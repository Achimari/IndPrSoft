package lv.restaurant.core.database.ingredient;

import lv.restaurant.core.domain.Ingredient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrmIngredientRepository implements IngredientRepository{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }
}
