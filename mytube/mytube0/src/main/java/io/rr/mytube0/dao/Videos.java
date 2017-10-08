package io.rr.mytube0.dao;

import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

/**
 * @author roman.rudenko on 01-Sep-16.
 */
public class Videos {
    private final MongoCollection<Document> videosInfoCollection;
    // create a "video" namespace
    private final GridFS gfsVideo;

    public Videos(MongoDatabase tubeDatabase, DB tubeDB) {
        videosInfoCollection = tubeDatabase.getCollection("videos");
        this.gfsVideo = new GridFS(tubeDB, "video");
    }

    // Return a list of videos in descending order. Limit determines
    // how many videos are returned.
    public List<Document> findByDateDescending(int limit) {
        // Return a list of DBObjects, each one a video from the videos collection
        List<Document> videosInfo = null;
        Bson sort = descending("date");
        videosInfo = videosInfoCollection.find().sort(sort).limit(limit).into(new ArrayList<Document>());

        return videosInfo;
    }

    // Return a single video corresponding to a permalink
    public Video findByPermalink(String permalink) throws IOException {
        Document videoInfo = null;
        Bson filter = eq("permalink", permalink);
        videoInfo = videosInfoCollection.find(filter).first();
        GridFSDBFile gfsVideoFile = gfsVideo.findOne(permalink);

        return new Video(videoInfo, gfsVideoFile, permalink);
    }

    public String addVideo(String title, InputStream is, List<String> tags, String username) throws IOException {
        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();

        // Build the videoInfo object and insert it
        Document videoInfo = new Document();
        videoInfo.append("title", title).append("author", username).append("permalink", permalink).append("tags", tags)
                .append("comments", new ArrayList<Document>()).append("date", new Date());
        videosInfoCollection.insertOne(videoInfo);

        GridFSInputFile gfsFile = gfsVideo.createFile(is);
        gfsFile.setFilename(permalink);
        // save the video file into mongoDB
        gfsFile.save();

        return permalink;
    }

    // Append a comment to a video
    public void addVideoComment(String name, String email, String body, String permalink) {
        Document comment = new Document();
        comment.append("author", name).append("body", body);

        if (email != null && !email.equals("")) {
            // the provided email address
            comment.append("email", email);
        }

        videosInfoCollection.updateOne(eq("permalink", permalink), new Document("$push",
                new Document("comments", comment)));
    }
}
