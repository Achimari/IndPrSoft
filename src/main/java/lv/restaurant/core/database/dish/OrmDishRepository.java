package lv.restaurant.core.database.dish;

import lv.restaurant.core.domain.Dish;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmDishRepository implements DishRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Dish dish) {
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    public boolean deleteDishByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Dish " +
                "WHERE name = :name");
        query.setParameter("name", name);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Dish> getAllDishes() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d from Dish d", Dish.class)
                .getResultList();
    }
}
