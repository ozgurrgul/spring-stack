package demo.service;

import demo.BadRequestException;
import demo.domain.Token;
import demo.domain.User;
import demo.dto.AuthLoginDTO;
import demo.dto.AuthRegisterDTO;
import demo.dto.AuthResponseDTO;
import demo.repository.TokenRepository;
import demo.repository.UserRepository;
import demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

/**
 * Created by ozgur on 7/29/17.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Iterable<User> list() {
        return userRepository.findAll();
    }

}
