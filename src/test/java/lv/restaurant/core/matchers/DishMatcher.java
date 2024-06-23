package lv.restaurant.core.matchers;

import lv.restaurant.core.domain.Dish;
import org.mockito.ArgumentMatcher;

public class DishMatcher implements ArgumentMatcher<Dish> {
    private String name;
    private String description;
    private String type;
    private Double weight;
    private Double price;


    public DishMatcher(String name, String description, String type, Double weight, Double price ) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public boolean matches(Dish dish) {
        return dish.getName().equals(name)
                && dish.getDescription().equals(description)
                && dish.getType().equals(type)
                && dish.getWeight() == weight
                && dish.getPrice() == price;
    }
}
