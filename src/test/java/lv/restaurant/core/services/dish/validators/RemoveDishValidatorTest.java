package lv.restaurant.core.services.dish.validators;

import lv.restaurant.core.requests.dish.RemoveDishRequest;
import lv.restaurant.core.responses.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveDishValidatorTest {
    @InjectMocks
    private RemoveDishValidator validator;

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        RemoveDishRequest request = new RemoveDishRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void shouldRemoveDish() {
        RemoveDishRequest request = new RemoveDishRequest("name");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}