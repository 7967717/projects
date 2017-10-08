package io.rr.mytube0.dao;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.rr.mytube0.utils.SecurityUtils;
import io.rr.mytube0.validation.GenericValidator;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * @author roman.rudenko on 01-Sep-16.
 */
public class Users extends GenericValidator<String> {
    private final MongoCollection<Document> usersCollection;
    private static final Logger log = LoggerFactory.getLogger(Users.class);
    private static final int MAX_FAIL_LOGIN_ATTEMPTS = 3;

    private static final List<Predicate<String>> VALIDATORS = new LinkedList<>();

    static {
        VALIDATORS.add(string -> string != null);
        VALIDATORS.add(string -> !string.isEmpty());
    }

    public Users(MongoDatabase tubeDatabase) {
        super(VALIDATORS);
        usersCollection = tubeDatabase.getCollection("users");
    }

    // validates that username is unique and insert into db
    public boolean addUser(String username, String password, String email) {
        if (usernameExists(username)) {
            log.info("Username already in use: " + username);
            return false;
        }

        String passwordHash = SecurityUtils.hashPassWithBCrypt(password);
        int failLoginAttempt = 0;
        Document user = new Document();
        user.append("username", username).append("password", passwordHash)
                .append("attempt", failLoginAttempt).append("blocked", false);

        if (email != null && !email.equals("")) {
            // the provided email address
            user.append("email", email);
        }

        try {
            usersCollection.insertOne(user);
            return true;
        } catch (MongoWriteException e) {
            log.info("Could not add user: " + username);
            throw e;
        }
    }

    private boolean usernameExists(String username) {
        Document user = usersCollection.find(eq("username", username)).first();
        return (user != null);
    }

    public Document validateLogin(String username, String password, Map<String, String> errors) {
        Document user;
        user = getUser(username);

        if (user == null) {
            log.info("User not in database");
            errors.put("login_error", "User not in database");
            return null;
        } else if (user.getBoolean("blocked")) {
            log.info("Account is blocked");
            errors.put("login_error", "Account is blocked");
            return null;
        }

        if (!validateLoginPassword(username, password, errors, user)) {
            return null;
        }

        return user;
    }

    private boolean validateLoginPassword(String username, String password, Map<String, String> errors, Document user) {
        String hashedPassword = user.get("password").toString();
        int failLoginAttempt = user.getInteger("attempt");

        if (SecurityUtils.checkPassWithBCrypt(password, hashedPassword)) {
            failLoginAttempt = 0;
            log.info("Login successful");
            usersCollection.updateOne(eq("username", username), set("attempt", failLoginAttempt));
        } else {
            failLoginAttempt++;
            if(failLoginAttempt < (MAX_FAIL_LOGIN_ATTEMPTS - 1)) {
                log.info("Submitted password is not a match");
                errors.put("login_error", "Invalid Password");
                usersCollection.updateOne(eq("username", username), set("attempt", failLoginAttempt));
            } else if (failLoginAttempt == (MAX_FAIL_LOGIN_ATTEMPTS - 1)) {
                log.info("Submitted password is not a match. ONE ATTEMPT REMAINING TO ENTER PASSWORD");
                errors.put("login_error", "Invalid Password. YOU HAVE ONE ATTEMPT REMAINING TO ENTER YOUR PASSWORD");
                usersCollection.updateOne(eq("username", username), set("attempt", failLoginAttempt));
            } else {
                failLoginAttempt = 0;
                log.info("Submitted password is not a match. ACCOUNT HAS BEEN BLOCKED");
                errors.put("login_error", "Invalid Password. YOUR ACCOUNT HAS BEEN BLOCKED");
                usersCollection.updateOne(eq("username", username)
                        , combine(set("blocked", true), set("attempt", failLoginAttempt)));
            }
            return false;
        }
        return true;
    }

    public boolean validatePassword(String username, String password, Map<String, String> errors) {
        Document user;
        user = getUser(username);
        String hashedPassword = user.get("password").toString();

        if(SecurityUtils.checkPassWithBCrypt(password, hashedPassword)){
            return true;
        }
        errors.put("password_error", "Invalid Password");
        return false;
    }

    public Document getUser(String username) {
        return usersCollection.find(eq("username", username)).first();
    }

    public Document getQuestions(String username, Map<String, String> errors) {
        Document user;
        user = getUser(username);

        if (user == null) {
            log.info("User not in database");
            errors.put("username_error", "User not in database");
            return null;
        } else if (user.get("question1") == null) {
            errors.put("username_error", "User do not have recovery questions. You could not recover account");
            return null;
        }

        return user;
    }


    public boolean checkAnswers(String username, String answer1, String answer2, Map<String, String> errors) {
        Document user;
        user = getUser(username);

        if(!user.get("answer1").equals(answer1)){
            errors.put("answer1_error", "Wrong answer");
            return false;
        }

        if(!user.get("answer2").equals(answer2)){
            errors.put("answer2_error", "Wrong answer");
            return false;
        }
        return true;
    }

    public void recoverUser(String username, String password) {
        Document user;
        user = getUser(username);

        String passwordHash = SecurityUtils.hashPassWithBCrypt(password);
        user.append("password", passwordHash);
        user.append("blocked", false);
        usersCollection.updateOne(eq("username", username), new Document("$set", user));
    }

    public boolean updateUser(Map<String, String> settings) {
        if (usernameExists(settings.get("newusername"))) {
            return false;
        }

        Document user;
        user = getUser(settings.get("username"));

        if(isValid(settings.get("newusername"))){
            user.append("username", settings.get("newusername"));
        }

        if(isValid(settings.get("newpassword"))){
            String passwordHash = SecurityUtils.hashPassWithBCrypt(settings.get("newpassword"));
            user.append("password", passwordHash);
        }

        if(isValid(settings.get("newemail"))){
            user.append("email", settings.get("newemail"));
        }

        if(isValid(settings.get("question1"))){
            user.append("question1", settings.get("question1"));
            user.append("answer1", settings.get("answer1"));
            user.append("question2", settings.get("question2"));
            user.append("answer2", settings.get("answer2"));
        }

        usersCollection.updateOne(eq("username", settings.get("username")), new Document("$set", user));

        return true;
    }

    // validates that the registration form has been filled out right and username conforms
    public boolean validateSignup(String username, String password, String verifyPassword, String email,
                                  Map<String, String> errors) {
        if (isValid(username)) {
            if (!validateUsername(username, errors)) {
                return false;
            }
        } else {
            errors.put("username_error", "Empty username.");
            return false;
        }

        if (isValid(password)) {
            if (!validatePassword(password, errors)) {
                return false;
            }
        } else {
            errors.put("password_error", "Empty password.");
            return false;
        }

        if (isValid(verifyPassword)) {
            if (!validateVerify(password, verifyPassword, errors)) {
                return false;
            }
        } else {
            errors.put("verify_error", "Empty verify.");
            return false;
        }

        if (!validateEmail(email, errors)) {
            return false;
        }

        return true;
    }

    // validates that the user settings form has been filled out right and settings conform
    public boolean validateUserSettings(Map<String, String> map) {
        if (isValid(map.get("newusername"))) {
            if (!validateUsername(map.get("newusername"), map)) {
                return false;
            }
        }

        if (inNotValid(map.get("password"))) {
            map.put("password_error", "Empty password.");
            return false;
        }

        if (isValid(map.get("newpassword"))) {
            if (!validatePassword(map.get("newpassword"), map)) {
                map.remove("password_error");
                map.put("newpassword_error", "Invalid password. Length at least 3 characters and maximum of 20.");
                return false;
            }
            if (!validateVerify(map.get("newpassword"), map.get("verify"), map)) {
                return false;
            }
        }

        if (isValid(map.get("newemail"))) {
            if (!validateEmail(map.get("newemail"), map)) {
                return false;
            }
        }

        if (isValid(map.get("question1"))) {
            boolean valid = true;
            if (inNotValid(map.get("answer1"))) {
                map.put("answer1_error", "Empty answer 1.");
                valid = false;
            }
            if (inNotValid(map.get("question2"))) {
                map.put("question2_error", "Empty question 2.");
                valid = false;
            }
            if (inNotValid(map.get("answer2"))) {
                map.put("answer2_error", "Empty answer 2.");
                valid = false;
            }
            return valid;
        }

        return true;
    }

    private boolean validateUsername(String username, Map<String, String> errors) {
        String USER_RE = "^[a-zA-Z0-9_-]{3,20}$";
        if (username.matches(USER_RE)) {
            return true;
        }
        errors.put("username_error", "Invalid username. Length at least 3 characters, just letters and numbers.");
        return false;
    }

    private boolean validatePassword(String password, Map<String, String> errors) {
        String PASS_RE = "^.{3,20}$";
        if (password.matches(PASS_RE)) {
            return true;
        }
        errors.put("password_error", "Invalid password. Length at least 3 characters and maximum of 20.");
        return false;
    }

    private boolean validateVerify(String password, String verifyPassword, Map<String, String> errors) {
        if (password.equals(verifyPassword)) {
            return true;
        }
        errors.put("verify_error", "Password must match.");
        return false;
    }

    private boolean validateEmail(String email, Map<String, String> errors) {
        if (isValid(email)) {
            String EMAIL_RE = "^[\\S]+@[\\S]+\\.[\\S]+$";
            if (!email.matches(EMAIL_RE)) {
                errors.put("email_error", "Invalid Email Address.");
                return false;
            }
        }
        return true;
    }

    public boolean validateAccountRecovery(String username, String password, String verify, Map<String, String> errors) {
        if (isValid(username)) {
            if (!validateUsername(username, errors)) {
                return false;
            }
        } else {
            errors.put("username_error", "Empty username.");
            return false;
        }

        if (isValid(password)) {
            if (!validatePassword(password, errors)) {
                return false;
            }
        } else {
            errors.put("password_error", "Empty password.");
            return false;
        }

        if (isValid(verify)) {
            if (!validateVerify(password, verify, errors)) {
                return false;
            }
        } else {
            errors.put("verify_error", "Empty verify.");
            return false;
        }

        return true;
    }
}
