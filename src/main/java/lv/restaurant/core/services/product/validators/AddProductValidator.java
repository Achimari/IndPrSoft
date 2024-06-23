package lv.restaurant.core.services.product.validators;

import lv.restaurant.core.requests.product.AddProductRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddProductRequest request) {
        validateName(request).ifPresent(errors::add);
        validateQuantity(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        validateDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddProductRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateQuantity(AddProductRequest request) {
        return (request.getQuantity() == null)
                ? Optional.of(new CoreError("Quantity", "Must be a valid double value"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(AddProductRequest request) {
        return (request.getPrice() == null)
                ? Optional.of(new CoreError("Price", "Must be a valid double value"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDate(AddProductRequest request) {
        return (request.getDate() == null)
                ? Optional.of(new CoreError("Date", "Must be a valid date"))
                : Optional.empty();
    }
}
