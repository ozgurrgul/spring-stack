package demo.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ozgur on 7/23/17.
 */
public class UserChangePasswordDTO {

    @NotBlank(message = "Old password can't be empty")
    @Length(min = 8, max = 50, message = "Old password must be 8-50 characters")
    public String oldPassword;

    @NotBlank(message = "New password can't be empty")
    @Length(min = 8, max = 50, message = "New password must be 8-50 characters")
    public String newPassword;
}
