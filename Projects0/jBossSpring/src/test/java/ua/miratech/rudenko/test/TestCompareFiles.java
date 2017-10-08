package ua.miratech.rudenko.test;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RomanR on 2/14/14.
 */
public class TestCompareFiles {

    public static String generateBufferedHash(File file)
            throws NoSuchAlgorithmException,
            FileNotFoundException, IOException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        InputStream is= new FileInputStream(file);

        byte[] buffer=new byte[8192];
        int read=0;

        while( (read = is.read(buffer)) > 0)
            md.update(buffer, 0, read);

        byte[] md5 = md.digest();
        BigInteger bi=new BigInteger(1, md5);

        return bi.toString(16);
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        File file1 = new File("/STORAGE/MAIN_CATALOGUE/136.pdf");
        File file2 = new File("/STORAGE/MAIN_CATALOGUE/139.pdf");
        File file3 = new File("/STORAGE/MAIN_CATALOGUE/138.pdf");
        File file4 = new File("/STORAGE/MAIN_CATALOGUE/137.pdf");

        System.out.println(FileUtils.contentEquals(file1, file2));
        System.out.println(generateBufferedHash(file1));
        System.out.println(generateBufferedHash(file2));
        System.out.println(generateBufferedHash(file3));
        System.out.println(generateBufferedHash(file4));
    }
}
