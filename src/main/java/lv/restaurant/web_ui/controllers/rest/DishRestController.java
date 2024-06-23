package lv.restaurant.web_ui.controllers.rest;

import lv.restaurant.core.requests.dish.AddDishRequest;
import lv.restaurant.core.requests.dish.GetRestaurantMenuRequest;
import lv.restaurant.core.requests.dish.RemoveDishRequest;
import lv.restaurant.core.responses.dish.AddDishResponse;
import lv.restaurant.core.responses.dish.GetRestaurantMenuResponse;
import lv.restaurant.core.responses.dish.RemoveDishResponse;
import lv.restaurant.core.services.dish.AddDishService;
import lv.restaurant.core.services.dish.GetRestaurantMenuService;
import lv.restaurant.core.services.dish.RemoveDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishRestController {

    @Autowired
    private AddDishService addDishService;

    @Autowired
    private RemoveDishService removeDishService;

    @Autowired
    private GetRestaurantMenuService getRestaurantMenuService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddDishResponse addDish(@RequestBody AddDishRequest addDishRequest) {
        return addDishService.execute(addDishRequest);
    }

    @DeleteMapping(path = "/{dishNameToDelete}",
            produces = "application/json")
    public RemoveDishResponse removeDish(@PathVariable String dishNameToDelete) {
        RemoveDishRequest removeDishRequest = new RemoveDishRequest(dishNameToDelete);
        return removeDishService.execute(removeDishRequest);
    }

    @GetMapping(path = "/showMenu", produces = "application/json")
    public GetRestaurantMenuResponse getRestaurantMenu() {
        GetRestaurantMenuRequest request = new GetRestaurantMenuRequest();
        return getRestaurantMenuService.execute(request);
    }
}
