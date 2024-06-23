package lv.restaurant.core.responses.dish;

import lv.restaurant.core.responses.CoreError;
import lv.restaurant.core.responses.CoreResponse;

import java.util.List;

public class RemoveDishResponse extends CoreResponse {

    private boolean dishNameToDelete;

    public RemoveDishResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDishNameDeleted() {
        return dishNameToDelete;
    }

    public RemoveDishResponse(boolean dishNameToDelete) {
        this.dishNameToDelete = dishNameToDelete;
    }
}
