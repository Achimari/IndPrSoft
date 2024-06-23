package lv.restaurant.core.services.dish;

import lv.restaurant.core.database.dish.DishRepository;
import lv.restaurant.core.domain.Dish;
import lv.restaurant.core.requests.dish.GetRestaurantMenuRequest;
import lv.restaurant.core.responses.dish.GetRestaurantMenuResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetRestaurantMenuServiceTest {
    @Mock
    DishRepository repository;
    @InjectMocks
    GetRestaurantMenuService service;

    @Test
    public void shouldReturnAllDishes() {
        GetRestaurantMenuRequest request = new GetRestaurantMenuRequest();
        Dish dish1 = new Dish("Margherita", "Margherita", "Pizza", 0.2, 4.99, true);
        Dish dish2 = new Dish("Marinara", "Marinara", "Pizza", 0.2, 6.99, true);
        Mockito.when(repository.getAllDishes())
                .thenReturn(List.of(dish1, dish2));
        GetRestaurantMenuResponse response = service.execute(request);
        assertEquals(response.getRestaurantMenu().size(), 2);
    }
}