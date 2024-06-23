package lv.restaurant.core.services.user.validators;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.RegistrationRequest;
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
public class RegistrationValidatorTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private RegistrationValidator validator;

    @Test
    public void shouldReturnErrorIfNameIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("", "admin", "admin@admin.lv", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfSurnameIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("admin", "", "admin@admin.lv", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Surname");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorsIfEmailIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfEmailDoNotContainSymbol() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "admin", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must contain @ symbol");
    }

    @Test
    public void shouldReturnErrorIfPasswordIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "admin@admin.lv", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfPasswordIsShort() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "admin@admin.lv", "adm");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must be at least 5 symbols");
    }

    @Test
    public void shouldReturnErrorsIfEmailAndPasswordAreEmpty() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
        assertEquals(errors.get(1).getField(), "Password");
        assertEquals(errors.get(1).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorIfEmailIsAlreadyTaken() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "admin@admin.lv", "admin");
        when(repository.isEmailRegistered(request.getEmail())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "admin@admin.lv is already taken!");
    }

    @Test
    public void shouldReturnEmptyErrorList() {
        RegistrationRequest request = new RegistrationRequest("admin", "admin", "admin@admin.lv", "admin");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}