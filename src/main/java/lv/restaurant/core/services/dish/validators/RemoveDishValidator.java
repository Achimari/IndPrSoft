package lv.restaurant.core.services.dish.validators;

import lv.restaurant.core.requests.dish.RemoveDishRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveDishValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(RemoveDishRequest request) {
        validateNameToRemove(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateNameToRemove(RemoveDishRequest request){
        return (request.getName() == null || request.getName().length() == 0)
                ? Optional.of(new CoreError("name", "Must not be empty"))
                : Optional.empty();
    }
}
