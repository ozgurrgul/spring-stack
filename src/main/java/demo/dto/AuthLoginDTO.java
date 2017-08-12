package demo.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by ozgur on 7/8/17.
 */
public class AuthLoginDTO {

    @NotBlank(message = "E-mail can't be empty")
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Must be valid e-mail")
    public String email;

    @NotBlank(message = "Password can't be empty")
    @Length(min = 8, max = 50, message = "Password must be 8-50 characters")
    public String password;

}
