package demo.controller;

import demo.domain.User;
import demo.repository.UserRepository;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public Iterable<User> list() {
        return userService.list();
    }

}
