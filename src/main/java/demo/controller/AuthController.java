package demo.controller;

import demo.dto.AuthLoginDTO;
import demo.dto.AuthRegisterDTO;
import demo.dto.AuthResponseDTO;
import demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AuthResponseDTO login(@Valid @RequestBody AuthLoginDTO authLoginDTO) {
        return authService.login(authLoginDTO);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponseDTO register(@Valid @RequestBody AuthRegisterDTO authRegisterModel) {
        return authService.register(authRegisterModel);
    }

}
