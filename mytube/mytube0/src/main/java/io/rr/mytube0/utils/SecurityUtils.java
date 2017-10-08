package io.rr.mytube0.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author roman.rudenko on 05-Sep-16.
 */
public class SecurityUtils {

    public static String hashPassWithBCrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassWithBCrypt(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
