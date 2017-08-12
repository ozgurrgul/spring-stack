package demo.controller;

import demo.domain.User;
import demo.dto.AuthLoginDTO;
import demo.dto.AuthRegisterDTO;
import demo.dto.AuthResponseDTO;
import demo.dto.UserChangePasswordDTO;
import demo.service.UserService;
import demo.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Created by ozgur on 7/8/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AuthResponseDTO login(@Valid @RequestBody AuthLoginDTO authLoginDTO) {
        return userService.login(authLoginDTO);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponseDTO register(@Valid @RequestBody AuthRegisterDTO authRegisterModel) {
        return userService.register(authRegisterModel);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<User> list() {
        return userService.list();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, value = "change_password")
    public void changePassword(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO, Authentication a) {
        userService.changePassword(userChangePasswordDTO, ControllerUtils.getUser(a));
    }
}
