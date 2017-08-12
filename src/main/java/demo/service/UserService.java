package demo.service;

import demo.BadRequestException;
import demo.dto.AuthLoginDTO;
import demo.dto.AuthRegisterDTO;
import demo.dto.AuthResponseDTO;
import demo.domain.Token;
import demo.dto.UserChangePasswordDTO;
import demo.repository.TokenRepository;
import demo.domain.User;
import demo.repository.UserRepository;
import demo.utils.ControllerUtils;
import demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * Created by ozgur on 7/29/17.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    SecurityHistoryService securityHistoryService;

    @Autowired
    HttpServletRequest request;

    @Transactional
    public AuthResponseDTO login(AuthLoginDTO authLoginDTO) {

        User tmpUser = userRepository.getByEmailAndPassword(authLoginDTO.email, authLoginDTO.password);

        if(tmpUser == null) {
            throw new BadRequestException("Wrong email or password");
        }

        Token token = newToken(tmpUser);
        tokenRepository.save(token);
        securityHistoryService.saveLoginHistory(tmpUser, ip(), ua());

        return new AuthResponseDTO(token.getTokenValue());
    }

    @Transactional
    public AuthResponseDTO register(AuthRegisterDTO authRegisterDTO) {

        if(isEmailExists(authRegisterDTO)) {
            throw new BadRequestException("E-mail exists in database");
        }

        User tmpUser = new User();
        tmpUser.setEmail(authRegisterDTO.email);
        tmpUser.setPassword(authRegisterDTO.password);

        userRepository.save(tmpUser);

        Token token = newToken(tmpUser);
        tokenRepository.save(token);
        securityHistoryService.saveRegisterHistory(tmpUser, ip(), ua());

        return new AuthResponseDTO(token.getTokenValue());
    }

    @Transactional
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public void changePassword(UserChangePasswordDTO userChangePasswordDTO, User user) {

        boolean isOldPasswordCorrect = user.getPassword().equals(userChangePasswordDTO.oldPassword);

        if(!isOldPasswordCorrect) {
            throw new BadRequestException("Old password is wrong");
        }

        user.setPassword(userChangePasswordDTO.newPassword);
        securityHistoryService.savePasswordChangeHistory(user, ip(), ua());

        userRepository.save(user);
    }

    private boolean isEmailExists(AuthRegisterDTO authRegisterModel) {
        return userRepository.getByEmail(authRegisterModel.email) != null;
    }

    private Token newToken(User tmpUser) {
        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token();
        token.setTokenValue(tokenValue);
        token.setUser(tmpUser);
        return token;
    }

    private String ip() {
        return ControllerUtils.getIp(request);
    }

    private String ua() {
        return request.getHeader("User-Agent");
    }

}
