package lv.restaurant.core.responses.product;

import lv.restaurant.core.domain.Product;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class AddProductResponse extends CoreResponse {

    private Product newProduct;

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductResponse(Product newProduct) {
        this.newProduct = newProduct;
    }

    public Product getNewProduct() {
        return newProduct;
    }
}
