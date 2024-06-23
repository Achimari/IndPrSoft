package lv.restaurant.core.services.user.validators;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.ChangePasswordRequest;
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
public class ChangePasswordValidatorTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private ChangePasswordValidator validator;

    @Test
    public void shouldReturnErrorIfEmailIsEmpty() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfPasswordIsEmpty() {
        ChangePasswordRequest request = new ChangePasswordRequest("", "admin@admin.lv");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfEmailDoNotContainSymbol() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must contain @ symbol");
    }

    @Test
    public void shouldReturnErrorIfEmailIsNotFound() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "admin@admin.lv");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "admin@admin.lv is not found!");
    }

    @Test
    public void shouldReturnEmptyErrorList() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "admin@admin.lv");
        when(repository.isEmailRegistered(request.getEmail())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}