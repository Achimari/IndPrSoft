package lv.restaurant.core.services.user;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.domain.User;
import lv.restaurant.core.requests.user.LoginRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.user.LoginResponse;
import lv.restaurant.core.services.user.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class LoginService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private LoginValidator validator;

    public LoginResponse execute(LoginRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginResponse(errors);
        }
        User loginUser = repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if (loginUser == null) {
            errors.add(new CoreError("E-mail or Password is not correct.", "User not found!"));
            return new LoginResponse(errors);
        } else {
            return new LoginResponse(loginUser);
        }
    }
}
