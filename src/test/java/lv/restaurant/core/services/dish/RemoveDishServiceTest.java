package lv.restaurant.core.services.dish;

import lv.restaurant.core.database.dish.DishRepository;
import lv.restaurant.core.requests.dish.RemoveDishRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.dish.RemoveDishResponse;
import lv.restaurant.core.services.dish.validators.RemoveDishValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveDishServiceTest {
    @Mock
    private DishRepository database;
    @Mock
    private RemoveDishValidator validator;
    @InjectMocks
    private RemoveDishService service;

    @Test
    public void shouldDeleteProductByName() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteDishByName("name")).thenReturn(true);
        RemoveDishRequest request = new RemoveDishRequest("name");
        RemoveDishResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDishNameDeleted());
    }

    @Test
    public void shouldReturnErrorWhenDishNameIsEmpty() {
        RemoveDishRequest request = new RemoveDishRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("nameToRemove", "Must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveDishResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "nameToRemove");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty");

    }
}