package am.smartCode.lesson1.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static String getValue (Cookie[] cookies, String name){
        String value = null;
        if (cookies == null) {
            return value;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("rememberMe")) {
                value = cookie.getValue();
            }
        }
        return value;
    }
}
