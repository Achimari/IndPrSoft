package lv.restaurant.core.services.product;

import lv.restaurant.core.database.product.ProductRepository;
import lv.restaurant.core.domain.Product;
import lv.restaurant.core.requests.product.AddProductRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.product.AddProductResponse;
import lv.restaurant.core.services.product.validators.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddProductService {
    @Autowired
    private ProductRepository database;
    @Autowired private AddProductValidator validator;

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product product = new Product(request.getName(), request.getQuantity(), request.getPrice(), request.getDate());
        database.save(product);
        System.out.println("New product was added to the list.");

        return new AddProductResponse(product);
    }
}
