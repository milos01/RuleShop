package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by milosandric on 06.07.17.
 */
@Entity
@Table(name="items")
public class Item {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private Double price;

    private Double number_left;

    private Date record_creation;

    private String record_status;

    private Double lager_min_state;

    private Boolean needOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ItemCategory itemCategory;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<Cart> cartItems;

    public Set<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public Boolean getNeedOrder() {
        return needOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNeedOrder(Boolean needOrder) {
        this.needOrder = needOrder;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumber_left() {
        return number_left;
    }

    public void setNumber_left(Double number_left) {
        this.number_left = number_left;
    }

    public Date getRecord_creation() {
        return record_creation;
    }

    public void setRecord_creation(Date record_creation) {
        this.record_creation = record_creation;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public Double getLager_min_state() {
        return lager_min_state;
    }

    public void setLager_min_state(Double lager_min_state) {
        this.lager_min_state = lager_min_state;
    }
}
