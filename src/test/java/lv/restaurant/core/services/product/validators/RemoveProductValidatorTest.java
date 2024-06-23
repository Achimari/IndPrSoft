package lv.restaurant.core.services.product.validators;

import lv.restaurant.core.requests.product.RemoveProductRequest;
import lv.restaurant.core.responses.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductValidatorTest {
    @InjectMocks
    private RemoveProductValidator validator = new RemoveProductValidator();

    @Test
    public void shouldReturnErrorWhenSearchedNameIsNull() {
        RemoveProductRequest request = new RemoveProductRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "nameToRemove");
        assertEquals(errors.get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldRemoveProduct() {
        RemoveProductRequest request = new RemoveProductRequest("name");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}