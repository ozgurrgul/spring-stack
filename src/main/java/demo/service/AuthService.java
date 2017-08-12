package demo.service;

import demo.BadRequestException;
import demo.dto.AuthLoginDTO;
import demo.dto.AuthRegisterDTO;
import demo.dto.AuthResponseDTO;
import demo.domain.Token;
import demo.repository.TokenRepository;
import demo.domain.User;
import demo.repository.UserRepository;
import demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ozgur on 7/29/17.
 */

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public AuthResponseDTO login(AuthLoginDTO authLoginDTO) {

        User tmpUser = userRepository.getByEmailAndPassword(authLoginDTO.email, authLoginDTO.password);

        if(tmpUser == null) {
            throw new BadRequestException("Wrong email or password");
        }

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponseDTO(tokenValue);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponseDTO register(AuthRegisterDTO authRegisterModel) {

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

        return new AuthResponseDTO(tokenValue);
    }

    private boolean isEmailExists(AuthRegisterDTO authRegisterModel) {
        return userRepository.getByEmail(authRegisterModel.email) != null;
    }

}
