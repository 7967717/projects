package io.rr.mytube0;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.rr.mytube0.dao.*;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author roman.rudenko on 02-Sep-16.
 */
public class TubeControllerTest {
    private Videos videos;
    private Users users;
    private Sessions sessions;
    private MongoCollection<Document> usersCollection;
    private MongoCollection<Document> sessionsCollection;
    private String movPath = "video/mov.mp4";
    private static final Logger log = LoggerFactory.getLogger(TubeControllerTest.class);

    @Before
    public void setUp() throws Exception {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final MongoDatabase tubeDatabase = mongoClient.getDatabase("tube");
        final DB tubeDB = mongoClient.getDB("tube");

        videos = new Videos(tubeDatabase, tubeDB);
        users = new Users(tubeDatabase);
        sessions = new Sessions(tubeDatabase);

        usersCollection = tubeDatabase.getCollection("users");
        sessionsCollection = tubeDatabase.getCollection("sessions");
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testAddVideo() throws Exception {
        String title = "video0";
        URL resource = this.getClass().getClassLoader().getResource(movPath);
        File file = new File(resource.getFile());
        InputStream is = new FileInputStream(file);
        String username = "user0";
        List<String> tagsArray = Arrays.asList("t0", "a0", "g0");

        String permalink = videos.addVideo(title, is, tagsArray, username);
        log.info(permalink);
    }

    @Test
    public void testAddUser() throws Exception {
        usersCollection.drop();

        String username, password, email;
        username = "testuser0";
        password = "testpassword0";
        email = "testuser0@email.email";

        Boolean added = users.addUser(username, password, email);
        assertTrue(added);

        added = users.addUser(username, password, email);
        assertFalse(added);

        Class userDAOClass = users.getClass();
        Method method = userDAOClass.getDeclaredMethod("usernameExists", String.class);
        method.setAccessible(true);

        boolean exists = (boolean) method.invoke(users, username);
        assertTrue(exists);

        username = "fakeuser0";
        exists = (boolean) method.invoke(users, username);
        assertFalse(exists);

//        usersCollection.drop();
    }

//    @Test
    public void dropUsersCollection(){
        usersCollection.drop();
    }

//    @Test
    public void dropSessionsCollection(){
        sessionsCollection.drop();
    }
}