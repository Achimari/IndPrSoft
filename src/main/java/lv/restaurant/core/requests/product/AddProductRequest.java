package lv.restaurant.core.requests.product;

import java.util.Date;

public class AddProductRequest {

    private Long id;
    private String name;
    private Double quantity;
    private Double price;
    private Date date;

    public AddProductRequest(String name, Double quantity, Double price, Date date){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public AddProductRequest(Long id,String name, Double quantity, Double price, Date date){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public AddProductRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
