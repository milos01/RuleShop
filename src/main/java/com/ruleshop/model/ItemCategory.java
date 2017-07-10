package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by milosandric on 06.07.17.
 */
@Entity
@Table(name="item_categorires")
public class ItemCategory {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private Boolean hasGlobaItemCat;

    private int max_discount_percent;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemCategory")
    private Set<Item> items;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public Boolean getHasGlobaItemCat() {
        return hasGlobaItemCat;
    }

    public void setHasGlobaItemCat(Boolean hasGlobaItemCat) {
        this.hasGlobaItemCat = hasGlobaItemCat;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Set<Item> getItems() {
        return items;
    }

    public int getMax_discount_percent() {
        return max_discount_percent;
    }

    public void setMax_discount_percent(int max_discount_percent) {
        this.max_discount_percent = max_discount_percent;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
