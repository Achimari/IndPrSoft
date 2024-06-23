package lv.restaurant.core.requests.product;

public class RemoveProductRequest {

    private String productNameToRemove;

    public RemoveProductRequest(String productNameToRemove) {
        this.productNameToRemove = productNameToRemove;
    }

    public RemoveProductRequest() {
    }

    public String getProductNameToRemove() {
        return productNameToRemove;
    }

    public void setProductNameToRemove(String productNameToRemove) {
        this.productNameToRemove = productNameToRemove;
    }
}
