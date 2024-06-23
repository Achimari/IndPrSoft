package lv.restaurant.web_ui.controllers.dish;

import lv.restaurant.core.requests.dish.GetRestaurantMenuRequest;
import lv.restaurant.core.responses.dish.GetRestaurantMenuResponse;
import lv.restaurant.core.services.dish.GetRestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllDishesController {

    @Autowired
    private GetRestaurantMenuService service;

    @GetMapping(value = "/getAllDishes")
    public String getAllDishes(ModelMap modelMap) {
        GetRestaurantMenuResponse response = service.execute(new GetRestaurantMenuRequest());

        modelMap.addAttribute("dishes", response.getRestaurantMenu());
        return "/getAllDishes";
    }

}
