package lv.restaurant.web_ui.controllers.user;

import lv.restaurant.core.requests.user.RegistrationRequest;
import lv.restaurant.core.responses.user.RegistrationResponse;
import lv.restaurant.core.services.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping(value = "/userRegistration")
    public String showRegistrationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RegistrationRequest());
        return "userRegistration";
    }

    @PostMapping("/userRegistration")
    public String processRegistrationRequest(@ModelAttribute(value = "request") RegistrationRequest request,
                                             ModelMap modelMap) {
        RegistrationResponse response = registrationService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "userRegistration";
        } else {
            return "redirect:/";
        }
    }

}
