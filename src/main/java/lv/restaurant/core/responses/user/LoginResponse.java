package lv.restaurant.core.responses.user;

import lv.restaurant.core.domain.User;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class LoginResponse extends CoreResponse {
    private User user;

    public LoginResponse(List<CoreError> errors) {
        super(errors);
    }

    public LoginResponse() {
    }

    public LoginResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
