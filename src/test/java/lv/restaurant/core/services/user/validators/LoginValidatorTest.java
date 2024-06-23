package lv.restaurant.core.services.user.validators;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.LoginRequest;
import lv.restaurant.core.responses.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginValidatorTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private LoginValidator validator;

    @Test
    public void shouldReturnErrorIfEmailIsEmpty() {
        LoginRequest request = new LoginRequest("", "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfEmailDoNotContainSymbol() {
        LoginRequest request = new LoginRequest("admin", "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email");
        assertEquals(errors.get(0).getMessage(), "Email must contain @ symbol");
    }

    @Test
    public void shouldReturnErrorIfPasswordIsEmpty() {
        LoginRequest request = new LoginRequest("admin@admin.lv", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorsEmailAndIfPasswordAreEmpty() {
        LoginRequest request = new LoginRequest("", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Email");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
        assertEquals(errors.get(1).getField(), "Password");
        assertEquals(errors.get(1).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfUserIsNotRegistered() {
        LoginRequest request = new LoginRequest("admin@admin.lv", "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email or Password is not correct.");
        assertEquals(errors.get(0).getMessage(), "User is not found!");
    }

    @Test
    public void shouldReturnEmptyErrorList() {
        LoginRequest request = new LoginRequest("admin@admin.lv", "admin");
        when(repository.isUserRegistered(request.getEmail(), request.getPassword())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}