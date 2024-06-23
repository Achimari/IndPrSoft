package lv.restaurant.core.database.user;

import lv.restaurant.core.domain.User;

public interface UserRepository {

    void save(User user);

    boolean changeUserPassword(String password, String email);

    boolean isUserRegistered(String email, String password);

    User findUserByEmailAndPassword(String email, String password);

    boolean isEmailRegistered(String email);
}
