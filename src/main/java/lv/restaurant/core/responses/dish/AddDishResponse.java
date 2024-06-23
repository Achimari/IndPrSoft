package lv.restaurant.core.responses.dish;

import lv.restaurant.core.domain.Dish;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class AddDishResponse extends CoreResponse {

    private Dish newDish;

    public AddDishResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDishResponse(Dish newDish) {
        this.newDish = newDish;
    }

    public Dish getNewDish() {
        return newDish;
    }
}
