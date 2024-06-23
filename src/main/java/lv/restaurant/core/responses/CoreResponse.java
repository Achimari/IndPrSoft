package lv.restaurant.core.responses;

import java.util.List;

public abstract class CoreResponse {
    private List<CoreError> errors;
    private String type;
    private CoreError error;

    public CoreResponse() { }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }


    public CoreResponse(List<CoreError> errors, String type) {
        this.errors = errors;
        this.type = type;
    }

    public List<CoreError> getErrors() {
        return errors;
    }


    public boolean hasErrors() {
        return (errors != null && !errors.isEmpty());
    }
}
