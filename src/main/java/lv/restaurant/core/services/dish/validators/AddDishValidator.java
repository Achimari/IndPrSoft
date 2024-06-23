package lv.restaurant.core.services.dish.validators;

import lv.restaurant.core.requests.dish.AddDishRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddDishValidator {
    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(AddDishRequest request) {
        validateName(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validateType(request).ifPresent(errors::add);
        validateWeight(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddDishRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDescription(AddDishRequest request) {
        return (request.getDescription() == null || request.getDescription().isEmpty())
                ? Optional.of(new CoreError("Description", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateType(AddDishRequest request) {
        return (request.getType() == null || request.getType().isEmpty())
                ? Optional.of(new CoreError("Type", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateWeight(AddDishRequest request) {

        Double weight = request.getWeight();

        return (weight == null)
                ? Optional.of(new CoreError("Weight", "Must be a valid double value"))
                : Optional.empty();

    }

    private Optional<CoreError> validatePrice(AddDishRequest request) {

        Double price = request.getPrice();

        return (price == null)
                ? Optional.of(new CoreError("Price", "Must be a valid double value"))
                : Optional.empty();

    }
}
