package lv.restaurant.core.services.dish;

import lv.restaurant.core.database.dish.DishRepository;
import lv.restaurant.core.requests.dish.RemoveDishRequest;
import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.dish.RemoveDishResponse;
import lv.restaurant.core.services.dish.validators.RemoveDishValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveDishService {

    @Autowired
    private DishRepository database;
    @Autowired
    private RemoveDishValidator validator;

    public RemoveDishResponse execute(RemoveDishRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new RemoveDishResponse(errors);
        }
        boolean isDishDeleted = database.deleteDishByName(request.getName());
        return new RemoveDishResponse(isDishDeleted);
    }
}
