package lv.restaurant.core.services.user;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.ChangePasswordRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.user.ChangePasswordResponse;
import lv.restaurant.core.services.user.validators.ChangePasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ChangePasswordService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ChangePasswordValidator validator;

    public ChangePasswordResponse execute(ChangePasswordRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangePasswordResponse(errors);
        }
        return new ChangePasswordResponse(repository.changeUserPassword(request.getPassword(), request.getEmail()));
    }
}
