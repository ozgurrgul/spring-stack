package demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Created by ozgur on 7/8/17.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AuthResponse login(@Valid @RequestBody AuthLoginDTO authLoginDTO) {
        return authService.login(authLoginDTO);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponse register(@Valid @RequestBody AuthRegisterDTO authRegisterModel) {
        return authService.register(authRegisterModel);
    }

}
