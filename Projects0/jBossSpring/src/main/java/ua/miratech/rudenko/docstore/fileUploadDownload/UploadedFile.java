package ua.miratech.rudenko.docstore.fileUploadDownload;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RomanR on 1/28/14.
 */
public class UploadedFile {

    public static final Logger LOG = Logger.getLogger("rootLogger");
    private static final String FILE_HASH = "MD5";

    private MultipartFile file = null;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String generateBufferedHash(InputStream is) {

        LOG.info("generating MD5 hash");

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(FILE_HASH);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[8192];
        int read = 0;

        try {
            while ((read = is.read(buffer)) > 0)
                md.update(buffer, 0, read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] md5 = md.digest();
        BigInteger bi = new BigInteger(1, md5);

        return bi.toString(16);
    }

    public void saveUploadedFile(InputStream inputStream, String path, String fileName) {

        OutputStream outputStream = null;

        try {

            File newFile = new File(path, fileName);
            LOG.info("created file - " + fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            LOG.info("start recording to file");

            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            LOG.info("start OutputStream");

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            LOG.info("failed to save file, cause " + e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
