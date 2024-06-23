package lv.restaurant.core.database.product;

import lv.restaurant.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrmProductRepository implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteProductByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Product WHERE name = :name");
        query.setParameter("name", name);
        int result = query.executeUpdate();
        return result == 1;
    }

}
