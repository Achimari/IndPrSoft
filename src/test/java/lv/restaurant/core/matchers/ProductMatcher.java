package lv.restaurant.core.matchers;

import lv.restaurant.core.domain.Product;
import org.mockito.ArgumentMatcher;

public class ProductMatcher implements ArgumentMatcher<Product> {
    private String name;

    public ProductMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(name);
    }
}
