package lv.restaurant.core.responses.dish;

import lv.restaurant.core.domain.Dish;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class GetRestaurantMenuResponse extends CoreResponse {

    private List<Dish> restaurantMenu;

    public GetRestaurantMenuResponse(List<CoreError> errors, List<Dish> restaurantMenu) {
        super(errors);
        this.restaurantMenu = restaurantMenu;
    }

    public GetRestaurantMenuResponse(List<Dish> restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }

    public List<Dish> getRestaurantMenu() {
        return restaurantMenu;
    }

}
