package lv.restaurant.web_ui.controllers.rest;

import lv.restaurant.core.requests.product.AddProductRequest;
import lv.restaurant.core.requests.product.RemoveProductRequest;
import lv.restaurant.core.responses.product.AddProductResponse;
import lv.restaurant.core.responses.product.RemoveProductResponse;
import lv.restaurant.core.services.product.AddProductService;
import lv.restaurant.core.services.product.RemoveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private AddProductService addProductService;

    @Autowired
    private RemoveProductService removeProductService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
        return addProductService.execute(request);
    }

    @DeleteMapping(path = "/{productNameToRemove}", produces = "application/json")
    public RemoveProductResponse deleteProduct(@PathVariable String productNameToRemove) {
        RemoveProductRequest request = new RemoveProductRequest(productNameToRemove);
        return removeProductService.execute(request);
    }

}
