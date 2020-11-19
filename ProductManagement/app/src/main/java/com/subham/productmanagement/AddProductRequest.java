package com.subham.productmanagement;

public class AddProductRequest {

    public String name;
    public String quantity;
    public int price;

    public AddProductRequest(String name, String quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public AddProductRequest(){super();}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
