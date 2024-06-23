package lv.restaurant.core.services.product.validators;

import lv.restaurant.core.requests.product.RemoveProductRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveProductValidator {
    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(RemoveProductRequest request) {
        validateNameToRemove(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateNameToRemove(RemoveProductRequest request) {
        return (request.getProductNameToRemove() == null || request.getProductNameToRemove().isEmpty())
                ? Optional.of(new CoreError("nameToRemove", "must not be empty"))
                : Optional.empty();
    }
}
