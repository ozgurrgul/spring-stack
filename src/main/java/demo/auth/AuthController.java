package demo.auth;

import demo.common.BadRequestException;
import demo.token.Token;
import demo.token.TokenRepository;
import demo.user.User;
import demo.user.UserRepository;
import demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    public AuthController(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AuthResponse login(@RequestBody AuthLoginModel authLoginModel) {

        User tmpUser = userRepository.getByEmailAndPassword(authLoginModel.email, authLoginModel.password);

        if(tmpUser == null) {
            throw new BadRequestException("Wrong email or password or year");
        }

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponse(tokenValue);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponse register(@RequestBody AuthRegisterModel authRegisterModel) {

        System.out.println(authRegisterModel.email);
        System.out.println(authRegisterModel.password);

        User user = userRepository.getByEmail(authRegisterModel.email);

        if(user != null) {
            throw new BadRequestException("Email already exists in database");
        }

        User tmpUser = new User(authRegisterModel.email, authRegisterModel.password);

        // persist user
        try {
            userRepository.save(tmpUser);
        } catch (Exception e) {
            throw e;
        }

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        try {
            tokenRepository.save(token);
        } catch (Exception e) {
            throw e;
        }

        return new AuthResponse(tokenValue);
    }

}
