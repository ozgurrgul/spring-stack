package demo.utils;

import java.util.UUID;

/**
 * Created by ozgur on 7/6/17.
 */
public class TokenUtils {

    public static String randomTokenValue() {
        return UUID.randomUUID().toString();
    }

}