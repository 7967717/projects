package io.rr.mytube0;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.rr.mytube0.dao.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.rr.mytube0.utils.Util.entry;
import static io.rr.mytube0.utils.Util.toEmptyValuesMap;
import static io.rr.mytube0.utils.Util.toQueryParamMap;
import static java.util.AbstractMap.*;
import static spark.Spark.*;

/**
 * @author roman.rudenko on 30-Aug-16.
 */
public class TubeController {
    private final Configuration cfg;
    private final Videos videos;
    private final Users users;
    private final Sessions sessions;
    private static final int PORT = 8082;
    private static TubeController controller;
    private static final Logger log = LoggerFactory.getLogger(TubeController.class);

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            controller = new TubeController("mongodb://localhost");
        } else {
            controller = new TubeController(args[0]);
        }
    }

    public TubeController(String mongoConnectionURL) throws IOException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConnectionURL));
        final MongoDatabase tubeDatabase = mongoClient.getDatabase("tube");
        final DB tubeDB = mongoClient.getDB("tube");

        videos = new Videos(tubeDatabase, tubeDB);
        users = new Users(tubeDatabase);
        sessions = new Sessions(tubeDatabase);

        cfg = createFreemarkerConfiguration();
        setPort(PORT);
        initializeRoutes();
    }

    abstract class FreemarkerBasedRoute implements Route {
        final Template template;

        /**
         * Constructor
         */
        protected FreemarkerBasedRoute(final String templateName) throws IOException {
            template = cfg.getTemplate(templateName);
        }

        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request, final Response response, final Writer writer)
                throws IOException, TemplateException;

    }

    private void initializeRoutes() throws IOException {
        // this is the tube home page
        get("/", new FreemarkerBasedRoute("tube_template.ftl") {
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                String username = sessions.findUserNameBySessionId(getSessionCookie(request));

                List<Document> videos = TubeController.this.videos.findByDateDescending(10);
                SimpleHash root = new SimpleHash();

                root.put("myvideos", videos);
                if (username != null) {
                    root.put("username", username);
                }

                template.process(root, writer);
            }
        });

        // used to display actual tube video detail page
        get("/video/:permalink", new FreemarkerBasedRoute("entry_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                String permalink = request.params(":permalink");
                log.info("/video: get " + permalink);

                Video video = videos.findByPermalink(permalink);
                if (video == null) {
                    response.redirect("/video_not_found");
                } else {
                    // empty comment to hold new comment in form at bottom of tube entry detail page
                    SimpleHash newComment = new SimpleHash(toEmptyValuesMap("name", "email", "body"));

                    SimpleHash root = new SimpleHash(
                            Stream.of(
                                    entry("videoInfo", video.getVideoInfo()),
                                    entry("path", video.getPath()),
                                    entry("comments", newComment)).
                                    collect(Collectors.toMap(Entry::getKey, Entry::getValue)));

                    template.process(root, writer);
                }
            }
        });

        // handle the signup user
        post("/signup", new FreemarkerBasedRoute("signup.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                String email = queryParam("email", request);
                String username = queryParam("username", request);
                String password = queryParam("password", request);

                Map<String, String> root = toQueryParamMap(request, "username", "email");

                if (users.validateSignup(username, password, request.queryParams("verify"), email, root)) {
                    // good user
                    log.info("Signup: Creating user with: " + username + " " + password);
                    if (!users.addUser(username, password, email)) {
                        // duplicate user
                        root.put("username_error", "Username already in use, Please choose another");
                        template.process(root, writer);
                    } else {
                        // good user, let's start a session
                        String sessionID = sessions.startSession(username);
                        log.info("Session ID is" + sessionID);
                        response.raw().addCookie(new Cookie("session", sessionID));
                        response.redirect("/welcome");
                    }
                } else {
                    // bad signup
                    log.info("User Registration did not validate");
                    template.process(root, writer);
                }
            }
        });

        // present signup form for tube
        get("/signup", new FreemarkerBasedRoute("signup.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer)
                    throws IOException, TemplateException {

                SimpleHash root = new SimpleHash(toEmptyValuesMap("username", "password", "email", "password_error",
                        "username_error", "email_error", "verify_error"));
                template.process(root, writer);
            }
        });

        // present user settings form for tube
        get("/usersettings", new FreemarkerBasedRoute("user_settings.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer)
                    throws IOException, TemplateException {

                String cookie = getSessionCookie(request);
                String username = sessions.findUserNameBySessionId(cookie);
                Document user = users.getUser(username);

                // initialize values for the form.
                SimpleHash root = new SimpleHash(toEmptyValuesMap("newusername", "username_error", "password", "password_error",
                        "newpassword", "newpassword_error", "verify", "verify_error", "newemail",
                        "newemail_error"));
                root.putAll(
                        Stream.of(
                                entry("username", username),
                                entry("email", user.getString("email") != null ? user.getString("email") : ""),
                                entry("question1", user.getString("question1") != null ? user.getString("question1") : ""),
                                entry("answer1", user.getString("answer1") != null ? user.getString("answer1") : ""),
                                entry("question2", user.getString("question2") != null ? user.getString("question2") : ""),
                                entry("answer2", user.getString("answer2") != null ? user.getString("answer2") : ""))
                                .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                );
                template.process(root, writer);
            }
        });

        // handle the user settings
        post("/usersettings", new FreemarkerBasedRoute("user_settings.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                String username = queryParam("username", request);
                String newusername = queryParam("newusername", request);
                String password = queryParam("password", request);

                Map<String, String> root = toQueryParamMap(request, "username", "newusername", "password", "newpassword", "verify",
                        "email", "newemail", "question1", "answer1", "question2", "answer2");

                if (users.validateUserSettings(root)) {
                    if (users.validatePassword(username, password, root)) {
                        if (!users.updateUser(root)) {
                            // duplicate user
                            root.put("username_error", "Username already in use, Please choose another");
                            log.info("Username already in use: " + username);
                        } else {
                            // good user, updated
                            log.info("Signup: Updated settings of user : " + username);
                            if (users.isValid(newusername)) {
                                response.redirect("/logout");
                            }
                        }
                    }
                    log.info("Invalid Password");
                } else {
                    // bad settings
                    log.info("User Settings did not validate");
                }
                template.process(root, writer);

            }
        });

        get("/welcome", new FreemarkerBasedRoute("welcome.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                String cookie = getSessionCookie(request);
                String username = sessions.findUserNameBySessionId(cookie);

                if (username == null) {
                    log.info("welcome() can't identify the user, redirecting to signup");
                    response.redirect("/signup");
                } else {
                    SimpleHash root = new SimpleHash();
                    root.put("username", username);
                    template.process(root, writer);
                }
            }
        });

        // will present the form used to process new tube videos
        get("/newvideo", new FreemarkerBasedRoute("newvideo_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                // get cookie
                String username = sessions.findUserNameBySessionId(getSessionCookie(request));

                if (username == null) {
                    // looks like a bad request. user is not logged in
                    response.redirect("/login");
                } else {
                    SimpleHash root = new SimpleHash();
                    root.put("username", username);
                    template.process(root, writer);
                }
            }
        });

        // handle the new video upload
        post("/newvideo", new FreemarkerBasedRoute("newvideo_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer)
                    throws IOException, TemplateException {
                InputStream is = null;
                request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
                try (InputStream in = request.raw().getPart("video").getInputStream()) {
                    is = in;
                } catch (ServletException e) {
                    log.info("could not upload video: " + e);
                }

                String title = queryParam("subject", request);
                String tags = queryParam("tags", request);
                String username = sessions.findUserNameBySessionId(getSessionCookie(request));

                if (username == null) {
                    response.redirect("/login");    // only logged in users can upload to tube
                } else if (title.isEmpty() || is == null) {
                    // redisplay page with errors
                    SimpleHash root = new SimpleHash(
                            Stream.of(
                                    entry("errors", "video must contain a title or upload file is empty."),
                                    entry("subject", title),
                                    entry("username", username),
                                    entry("tags", tags))
                                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                    );
                    template.process(root, writer);
                } else {
                    ArrayList<String> tagsArray = extractTags(tags);
                    String permalink = videos.addVideo(title, is, tagsArray, username);
                    // now redirect to the tube permalink
                    response.redirect("/video/" + permalink);
                }
            }
        });

        get("/welcome", new FreemarkerBasedRoute("welcome.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                String cookie = getSessionCookie(request);
                String username = sessions.findUserNameBySessionId(cookie);

                if (username == null) {
                    log.info("welcome() can't identify the user, redirecting to signup");
                    response.redirect("/signup");
                } else {
                    SimpleHash root = new SimpleHash();
                    root.put("username", username);
                    template.process(root, writer);
                }
            }
        });

        // process a new comment
        post("/newcomment", new FreemarkerBasedRoute("entry_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer)
                    throws IOException, TemplateException {
                String name = queryParam("commentName", request);
                String email = queryParam("commentEmail", request);
                String body = queryParam("commentBody", request);
                String permalink = queryParam("permalink", request);

                Video video = videos.findByPermalink(permalink);
                if (video == null) {
                    response.redirect("/video_not_found");
                }
                // check that comment is good
                else if (name.equals("") || body.equals("")) {
                    // bounce this back to the user for correction
                    Map<String, String> comment = toQueryParamMap(request, "name", "email", "body");

                    SimpleHash root = new SimpleHash(
                            Stream.of(
                                    entry("comments", comment),
                                    entry("videoInfo", video.getVideoInfo()),
                                    entry("errors", "Video must contain your name and an actual comment"))
                                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                    );

                    template.process(root, writer);
                    template.process(comment, writer);
                } else {
                    videos.addVideoComment(name, email, body, permalink);
                    response.redirect("/video/" + permalink);
                }
            }
        });

        // present the login page
        get("/login", new FreemarkerBasedRoute("login.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash(toEmptyValuesMap("username", "login_error"));
                template.process(root, writer);
            }
        });

        // process output coming from login form. On success redirect folks to the welcome page
        // on failure, just return an error and let them try again.
        post("/login", new FreemarkerBasedRoute("login.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                String username = queryParam("username", request);
                String password = queryParam("password", request);
                log.info("Login: User submitted: " + username + "  " + password);

                Map<String, String> root = new HashMap<String, String>(
                        Stream.of(
                                entry("username", StringEscapeUtils.escapeHtml4(username)),
                                entry("password", ""))
                                .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                );

                Document user = users.validateLogin(username, password, root);

                if (user != null) {
                    // valid user, let's log them in
                    String sessionID = sessions.startSession(user.get("username").toString());

                    if (sessionID == null) {
                        response.redirect("/internal_error");
                    } else {
                        // set the cookie for the user's browser
                        response.raw().addCookie(new Cookie("session", sessionID));
                        response.redirect("/welcome");
                    }
                } else {
                    template.process(root, writer);
                }
            }
        });

        // present the Account recovery page
        get("/recovery", new FreemarkerBasedRoute("account_recovery.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash(toEmptyValuesMap("username", "username_error", "question1",
                        "question1_error", "answer1", "answer1_error", "question2", "question2_error", "answer2",
                        "answer2_error"));
                template.process(root, writer);
            }
        });

        // process output coming from Account recovery form. On success redirect folks to the welcome page
        // on failure, just return an error and let them try again.
        post("/recovery", new FreemarkerBasedRoute("account_recovery.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                String username = queryParam("username", request);
                String question1 = queryParam("question1", request);
                String answer1 = queryParam("answer1", request);
                String answer2 = queryParam("answer2", request);
                log.info("Account recovery: User : " + username);

                Map<String, String> root = toQueryParamMap(request, "username", "question1", "answer1", "question2", "answer2");

                if (users.inNotValid(username)) {
                    root.put("username_error", "Empty username.");
                } else {
                    Document user = users.getQuestions(username, root);
                    if (user != null) {
                        if (users.inNotValid(question1)) {
                            root.put("question1", user.get("question1").toString());
                            root.put("question2", user.get("question2").toString());
                        } else if (users.checkAnswers(username, answer1, answer2, root)) {
                            response.redirect("/recover");
                        }
                    }
                }
                template.process(root, writer);
            }
        });

        // handle the user recover
        post("/recover", new FreemarkerBasedRoute("recover.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                String username = queryParam("username", request);
                String password = queryParam("password", request);

                Map<String, String> root = toQueryParamMap(request, "username");

                if (users.validateAccountRecovery(username, password, request.queryParams("verify"), root)) {
                    users.recoverUser(username, password);
                    response.redirect("/login");
                }
                template.process(root, writer);
            }
        });

        // present recover form for tube
        get("/recover", new FreemarkerBasedRoute("recover.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer)
                    throws IOException, TemplateException {

                SimpleHash root = new SimpleHash(toEmptyValuesMap("username", "password", "password_error",
                        "username_error", "verify_error"));
                template.process(root, writer);
            }
        });

        // tells the user that the URL is dead
        get("/video_not_found", new FreemarkerBasedRoute("video_not_found.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash();
                template.process(root, writer);
            }
        });

        // allows the user to logout of the tube
        get("/logout", new FreemarkerBasedRoute("signup.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                String sessionID = getSessionCookie(request);

                if (sessionID == null) {
                    // no session to end
                    response.redirect("/login");
                } else {
                    // deletes from session table
                    sessions.endSession(sessionID);

                    // this should delete the cookie
                    Cookie c = getSessionCookieActual(request);
                    c.setMaxAge(0);

                    response.raw().addCookie(c);
                    response.redirect("/login");
                }
            }
        });

        // used to process internal errors
        get("/internal_error", new FreemarkerBasedRoute("error_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash();
                root.put("error", "System has encountered an error.");
                template.process(root, writer);
            }
        });
    }

    private String queryParam(String param, Request request) {
        return StringEscapeUtils.escapeHtml4(request.queryParams(param));
    }

    // helper function to get session cookie as string
    private String getSessionCookie(final Request request) {
        if (request.raw().getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.raw().getCookies()) {
            if (cookie.getName().equals("session")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    // helper function to get session cookie as string
    private Cookie getSessionCookieActual(final Request request) {
        if (request.raw().getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.raw().getCookies()) {
            if (cookie.getName().equals("session")) {
                return cookie;
            }
        }
        return null;
    }

    // tags the tags string and put it into an array
    private ArrayList<String> extractTags(String tags) {
        tags = tags.replaceAll("\\s", "");
        String tagArray[] = tags.split(",");

        // let's clean it up, removing the empty string and removing dups
        ArrayList<String> cleaned = new ArrayList<String>();
        for (String tag : tagArray) {
            if (!tag.equals("") && !cleaned.contains(tag)) {
                cleaned.add(tag);
            }
        }

        return cleaned;
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(TubeController.class, "/freemarker");
        return retVal;
    }
}
