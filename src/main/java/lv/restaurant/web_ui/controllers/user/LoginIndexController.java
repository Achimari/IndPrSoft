package lv.restaurant.web_ui.controllers.user;

import lv.restaurant.core.requests.user.LoginRequest;
import lv.restaurant.core.responses.user.LoginResponse;
import lv.restaurant.core.services.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginIndexController {
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/")
    public String showLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new LoginRequest());
        return "index";
    }

    @PostMapping("/")
    public String processLoginRequest(@ModelAttribute(value = "request") LoginRequest request,
                                      ModelMap modelMap) {
        LoginResponse response = loginService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "index";
        } else {
            return "mainPage";
        }
    }
}
