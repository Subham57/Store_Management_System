package com.subham.productmanagement;

import java.util.ArrayList;

public class ProductList {
    public String status;
    public Products products;

    public ProductList() {
        super();
    }

    public ProductList(String status,Products products) {
        this.products = products;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
