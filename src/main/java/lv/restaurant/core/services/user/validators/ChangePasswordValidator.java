package lv.restaurant.core.services.user.validators;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.ChangePasswordRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangePasswordValidator {
    @Autowired
    private UserRepository repository;

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(ChangePasswordRequest request) {
        validateEmail(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateRegistration(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePassword(ChangePasswordRequest request) {
        return (request.getPassword() == null || request.getPassword().isEmpty())
                ? Optional.of(new CoreError("Password", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmail(ChangePasswordRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return Optional.of(new CoreError("E-mail", "Must not be empty"));
        } else if (!request.getEmail().contains("@")) {
            return Optional.of(new CoreError("E-mail", "Must contain @ symbol"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegistration(ChangePasswordRequest request) {
        return (!repository.isEmailRegistered(request.getEmail()))
                ? Optional.of(new CoreError("E-mail", request.getEmail() + " is not found!"))
                : Optional.empty();
    }
}
