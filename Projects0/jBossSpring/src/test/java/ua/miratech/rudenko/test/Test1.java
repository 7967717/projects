package ua.miratech.rudenko.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RomanR on 3/3/14.
 */
public class Test1 {
    public static void main(String[] args) {
        String string = new String();
        string = "01-12-2014";
        Date d = new Date();
        String date = new String();
        DateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

        try {
            d = sdf.parse(string);
            date = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date);
    }
}
