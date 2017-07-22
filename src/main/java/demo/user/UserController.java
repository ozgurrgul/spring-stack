package demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public Iterable<User> list() {
        return userRepository.findAll();
    }

}
