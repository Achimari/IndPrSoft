package lv.restaurant.core.services.product;

import lv.restaurant.core.database.product.ProductRepository;
import lv.restaurant.core.requests.product.AddProductRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.product.AddProductResponse;
import lv.restaurant.core.services.product.validators.AddProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {
    @Mock
    ProductRepository database;
    @Mock
    private AddProductValidator validator;
    @InjectMocks
    AddProductService service;

    @Test
    public void shouldSuccessfullyAdd() throws ParseException {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("name", 1.0, 2.0,
                new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2021"));
        AddProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() throws ParseException {
        AddProductRequest request = new AddProductRequest(null, 1.0, 2.0,
                new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2021"));
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");

        Mockito.verifyNoInteractions(database);
    }
}