package lv.restaurant.core.services.user;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.domain.User;
import lv.restaurant.core.requests.user.LoginRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.user.LoginResponse;
import lv.restaurant.core.services.user.validators.LoginValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private LoginValidator validator;
    @InjectMocks
    LoginService service;

    @Test
    public void shouldLoginUser() {
        User user = mock(User.class);
        LoginRequest request = new LoginRequest("admin@admin.lv", "admin");
        when(repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword())).thenReturn(user);
        LoginResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotLoginUser() {
        LoginRequest request = new LoginRequest("admin@admin.lv", "");
        List<CoreError> errors = new ArrayList<>(List.of(new CoreError("Password", "Must not be empty")));
        when(validator.validate(request)).thenReturn(errors);
        LoginResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldNotLoginBecauseUserNotFound() {
        LoginRequest request = new LoginRequest("admin@admin.lv", "admin");
        when(repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword())).thenReturn(null);
        LoginResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        List<CoreError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail or Password is not correct.");
        assertEquals(errors.get(0).getMessage(), "User not found!");
    }
}