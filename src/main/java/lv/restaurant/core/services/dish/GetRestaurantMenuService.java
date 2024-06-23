package lv.restaurant.core.services.dish;

import lv.restaurant.core.database.dish.DishRepository;
import lv.restaurant.core.domain.Dish;
import lv.restaurant.core.requests.dish.GetRestaurantMenuRequest;
import lv.restaurant.core.responses.dish.GetRestaurantMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetRestaurantMenuService {
    @Autowired
    private DishRepository repository;

    public GetRestaurantMenuResponse execute(GetRestaurantMenuRequest request) {
        List<Dish> restaurantMenu = repository.getAllDishes();
        return new GetRestaurantMenuResponse(restaurantMenu);
    }
}
