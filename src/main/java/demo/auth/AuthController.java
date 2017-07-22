package demo.auth;

import demo.BadRequestException;
import demo.user.Token;
import demo.user.TokenRepository;
import demo.user.User;
import demo.user.UserRepository;
import demo.utils.ControllerUtils;
import demo.utils.TokenUtils;
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
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AuthResponse login(@Valid @RequestBody AuthLoginModel authLoginModel, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(ControllerUtils.getFormErrors(errors));
        }

        User tmpUser = userRepository.getByEmailAndPassword(authLoginModel.email, authLoginModel.password);

        if(tmpUser == null) {
            throw new BadRequestException("Wrong email or password");
        }

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponse(tokenValue);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponse register(@Valid @RequestBody AuthRegisterModel authRegisterModel, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(ControllerUtils.getFormErrors(errors));
        }

        if(isEmailExists(authRegisterModel)) {
            throw new BadRequestException("E-mail exists in database");
        }

        User tmpUser = new User(authRegisterModel.email, authRegisterModel.password);

        // persist user
        userRepository.save(tmpUser);

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponse(tokenValue);
    }

    private boolean isEmailExists(AuthRegisterModel authRegisterModel) {
        return userRepository.getByEmail(authRegisterModel.email) != null;
    }

}
