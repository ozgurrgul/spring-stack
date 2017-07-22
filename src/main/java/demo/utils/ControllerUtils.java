package demo.utils;

import org.springframework.validation.Errors;

import java.util.stream.Collectors;

/**
 * Created by ozgur on 7/22/17.
 */
public class ControllerUtils {

    public static String getFormErrors(Errors errors) {

        return errors.getAllErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(","));

    }

}
