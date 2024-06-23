package lv.restaurant.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExitController {

    @GetMapping(value = "/exit")
    public String exitApp(ModelMap modelMap) {
        System.exit(0);
        return "redirect:/";
    }
}
