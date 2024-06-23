package lv.restaurant.core.database.product;

import lv.restaurant.core.domain.Product;

public interface ProductRepository {

    void save(Product product);

    boolean deleteProductByName(String name);

}
