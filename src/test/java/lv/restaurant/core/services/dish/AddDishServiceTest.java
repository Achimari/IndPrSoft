package lv.restaurant.core.services.dish;

import lv.restaurant.core.database.dish.DishRepository;
import lv.restaurant.core.matchers.DishMatcher;
import lv.restaurant.core.requests.dish.AddDishRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.dish.AddDishResponse;
import lv.restaurant.core.services.dish.validators.AddDishValidator;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddDishServiceTest {
    @Mock
    private DishRepository database;
    @Mock
    private AddDishValidator validator;
    @InjectMocks
    private AddDishService service;

    @Test
    public void shouldAddDishToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddDishRequest request = new AddDishRequest("name", "desc", "soup", 1.0, 2.0);
        AddDishResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).save(
                argThat(new DishMatcher("name", "desc", "soup", 1.0, 2.0)));
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddDishRequest request = new AddDishRequest(null, "desc", "soup", 1.0, 2.0);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddDishResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        Mockito.verifyNoInteractions(database);
    }
}