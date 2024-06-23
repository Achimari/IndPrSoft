package lv.restaurant.web_ui.controllers.rest;

import lv.restaurant.core.requests.user.ChangePasswordRequest;
import lv.restaurant.core.requests.user.LoginRequest;
import lv.restaurant.core.requests.user.RegistrationRequest;
import lv.restaurant.core.responses.user.ChangePasswordResponse;
import lv.restaurant.core.responses.user.LoginResponse;
import lv.restaurant.core.responses.user.RegistrationResponse;
import lv.restaurant.core.services.user.ChangePasswordService;
import lv.restaurant.core.services.user.LoginService;
import lv.restaurant.core.services.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private ChangePasswordService changePasswordService;
    @Autowired private LoginService loginService;
    @Autowired private RegistrationService registrationService;

    @PostMapping(path = "/userRegistration",
            consumes = "application/json",
            produces = "application/json")
    public RegistrationResponse registrationResponse(@RequestBody RegistrationRequest request){
        return registrationService.execute(request);
    }

    @PutMapping(path = "/changePassword",
            consumes = "application/json",
            produces = "application/json")
    public ChangePasswordResponse changePassword (@RequestBody ChangePasswordRequest request){
        return changePasswordService.execute(request);
    }

    @PostMapping(path = "/userLogin",
            consumes = "application/json",
            produces = "application/json")
    public LoginResponse login (@RequestBody LoginRequest request){
        return loginService.execute(request);
    }

}
