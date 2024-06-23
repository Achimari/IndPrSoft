package lv.restaurant.core.requests.dish;

public class RemoveDishRequest {

    private String name;

    public RemoveDishRequest(String name) {
        this.name = name;
    }

    public RemoveDishRequest() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
