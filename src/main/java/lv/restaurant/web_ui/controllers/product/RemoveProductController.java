package lv.restaurant.web_ui.controllers.product;

import lv.restaurant.core.requests.product.RemoveProductRequest;
import lv.restaurant.core.responses.product.RemoveProductResponse;
import lv.restaurant.core.services.product.RemoveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveProductController {

    @Autowired
    private RemoveProductService removeProductService;

    @GetMapping(value = "/removeProductFromTheList")
    public String showRemovedProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveProductRequest());
        return "/removeProductFromTheList";
    }

    @PostMapping(value = "/removeProductFromTheList")
    public String processProductRemoveRequest(@ModelAttribute(value = "request")
                                                      RemoveProductRequest request, ModelMap modelMap) {
        RemoveProductResponse response = removeProductService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeProductFromTheList";
        } else {
            return "mainPage";
        }
    }
}
