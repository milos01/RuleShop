package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by milosandric on 07.07.17.
 */
@Entity
@Table(name="sales")
public class Sale {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private Date sale_from;

    private Date sale_to;

    private int discount_percent;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale")
    private Set<ItemCategory> items;

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

    public Date getSale_from() {
        return sale_from;
    }

    public void setSale_from(Date sale_from) {
        this.sale_from = sale_from;
    }

    public Date getSale_to() {
        return sale_to;
    }

    public void setSale_to(Date sale_to) {
        this.sale_to = sale_to;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public Set<ItemCategory> getItems() {
        return items;
    }

    public void setItems(Set<ItemCategory> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sale_from=" + sale_from +
                ", sale_to=" + sale_to +
                ", discount_percent=" + discount_percent +
                ", items=" + items +
                '}';
    }
}
