package lv.restaurant.core.responses.user;

import lv.restaurant.core.domain.User;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class RegistrationResponse extends CoreResponse {
    private User newUser;

    public RegistrationResponse(List<CoreError> errors) {
        super(errors);
    }

    public RegistrationResponse(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }
}
