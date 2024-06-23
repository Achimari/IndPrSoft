package lv.restaurant.core.database.ingredient;

import lv.restaurant.core.domain.Ingredient;

public interface IngredientRepository {

    void save(Ingredient ingredient);

}
