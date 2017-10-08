package io.rr.mytube0.dao;

import com.mongodb.gridfs.GridFSDBFile;
import org.bson.Document;

import java.io.*;

/**
 * @author roman.rudenko on 01-Sep-16.
 */
public class Video {
    private Document videoInfo;
    private String path;
    private String ext = ".mp4";
    private String folder = "/tubevideo/";
    private String userhome = "user.home";

    public Video(Document videoInfo, GridFSDBFile gfsVideoFile, String permalink) throws IOException {
        this.videoInfo = videoInfo;
        this.path = System.getProperty(userhome) + folder + permalink + ext;

        File file = new File(path);
        gfsVideoFile.writeTo(file);
    }

    public Document getVideoInfo() {
        return videoInfo;
    }

    public String getPath() {
        return path;
    }
}
