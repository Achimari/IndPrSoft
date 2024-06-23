package lv.restaurant.core.services.user;

import lv.restaurant.core.database.user.UserRepository;
import lv.restaurant.core.requests.user.ChangePasswordRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.user.ChangePasswordResponse;
import lv.restaurant.core.services.user.validators.ChangePasswordValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private ChangePasswordValidator validator;
    @InjectMocks
    ChangePasswordService service;

    @Test
    public void shouldChangePassword() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "admin@admin.lv");
        ChangePasswordResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotChangePassword() {
        ChangePasswordRequest request = new ChangePasswordRequest("admina", "admin@admin.lv");
        List<CoreError> errors = new ArrayList<>(List.of(new CoreError("E-mail", "Must not be empty")));
        when(validator.validate(request)).thenReturn(errors);
        ChangePasswordResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }
}