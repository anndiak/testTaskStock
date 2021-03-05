package com.example.model;

import com.example.ProductCategory;

public class Product {

    private final int id;
    private final ProductCategory category;

    public Product(int id, ProductCategory category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public ProductCategory getCategory() {
        return category;
    }

}
