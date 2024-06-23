package lv.restaurant.core.services.user;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.domain.User;
import lv.restaurant.core.requests.user.RegistrationRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.user.RegistrationResponse;
import lv.restaurant.core.services.user.validators.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationValidator validator;

    public RegistrationResponse execute(RegistrationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RegistrationResponse(errors);
        }
        User user = new User(request.getName(),
                request.getSurname(),
                request.getEmail(),
                request.getPassword());
        repository.save(user);
        return new RegistrationResponse(user);
    }
}
