package lv.restaurant.core.responses.product;

import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class RemoveProductResponse extends CoreResponse {

    private boolean productNameToDelete;

    public RemoveProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveProductResponse(boolean productNameToDelete) {
        this.productNameToDelete = productNameToDelete;
    }

    public boolean isProductRemoved() {
        return productNameToDelete;
    }
}
