package lv.restaurant.core.services.product;

import lv.restaurant.core.database.product.ProductRepository;
import lv.restaurant.core.requests.product.RemoveProductRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.product.RemoveProductResponse;
import lv.restaurant.core.services.product.validators.RemoveProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveProductService {

    @Autowired
    private ProductRepository database;
    @Autowired
    private RemoveProductValidator validator;

    public RemoveProductResponse execute(RemoveProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveProductResponse(errors);
        }
        boolean isProductDeleted = database.deleteProductByName(request.getProductNameToRemove());
        return new RemoveProductResponse(isProductDeleted);
    }

}
