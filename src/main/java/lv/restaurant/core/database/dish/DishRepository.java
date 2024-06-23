package lv.restaurant.core.database.dish;

import lv.restaurant.core.domain.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository {

    void save(Dish dish);

    boolean deleteDishByName(String name);

    List<Dish> getAllDishes();
}
