package io.rr.mytube0.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sun.misc.BASE64Encoder;
import java.security.SecureRandom;
import static com.mongodb.client.model.Filters.eq;

/**
 * @author roman.rudenko on 01-Sep-16.
 */
public class Sessions {
    private final MongoCollection<Document> sessionsCollection;

    public Sessions(final MongoDatabase tubeDatabase) {
        sessionsCollection = tubeDatabase.getCollection("sessions");
    }

    public String findUserNameBySessionId(String sessionId) {
        Document session = getSession(sessionId);

        if (session == null) {
            return null;
        } else {
            return session.get("username").toString();
        }
    }

    // starts a new session in the sessions table
    public String startSession(String username) {
        // get 32 byte random number. that's a lot of bits.
        SecureRandom generator = new SecureRandom();
        byte randomBytes[] = new byte[32];
        generator.nextBytes(randomBytes);

        BASE64Encoder encoder = new BASE64Encoder();
        String sessionID = encoder.encode(randomBytes);

        // build the BSON object
        Document session = new Document("username", username)
                .append("_id", sessionID);
        sessionsCollection.insertOne(session);

        return session.getString("_id");
    }

    // ends the session by deleting it from the sesisons table
    public void endSession(String sessionID) {
        sessionsCollection.deleteOne(eq("_id", sessionID));
    }

    // retrieves the session from the sessions table
    public Document getSession(String sessionID) {
        return sessionsCollection.find(eq("_id", sessionID)).first();
    }
}
