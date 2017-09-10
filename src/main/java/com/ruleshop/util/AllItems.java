package com.ruleshop.util;

import com.ruleshop.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllItems implements Serializable {
    private List<Item> products = new ArrayList<Item>();

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }
}
