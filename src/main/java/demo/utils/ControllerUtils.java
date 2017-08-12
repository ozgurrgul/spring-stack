package demo.utils;

import demo.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Created by ozgur on 7/22/17.
 */
public class ControllerUtils {

    public static String getIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public static User getUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

}
