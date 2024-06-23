package lv.restaurant.core.services.user.validators;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.RegistrationRequest;
import lv.restaurant.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RegistrationValidator {
    @Autowired
    private UserRepository repository;

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(RegistrationRequest request) {
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        if(errors.isEmpty()) {
            validateRegistration(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateName(RegistrationRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSurname(RegistrationRequest request) {
        return (request.getName() == null || request.getSurname().isEmpty())
                ? Optional.of(new CoreError("Surname", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmail(RegistrationRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return Optional.of(new CoreError("E-mail", "Must not be empty"));
        } else if (!request.getEmail().contains("@")) {
            return Optional.of(new CoreError("E-mail", "Must contain @ symbol"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegistration(RegistrationRequest request) {
        if (repository.isEmailRegistered(request.getEmail())) {
            return Optional.of(new CoreError("E-mail", request.getEmail() + " is already taken!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePassword(RegistrationRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return Optional.of(new CoreError("Password", "Must not be empty"));
        } else if (request.getPassword().length() < 5) {
            return Optional.of(new CoreError("Password", "Must be at least 5 symbols"));
        }
        return Optional.empty();
    }
}
