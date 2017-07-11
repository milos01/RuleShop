package com.ruleshop.model;

import antlr.collections.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.*;

/**
 * Created by milosandric on 08.07.17.
 */
@Entity
@Table(name="bill_items")
public class BillItem {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(nullable=false, name = "item_id")
    private Item item;

    private int item_quantity;

    private int discount_percent;

    private Double final_price;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(nullable=false, name = "bill_id")
    private Bill bill;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "billItem")
    private Set<ItemDiscount> discounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ItemDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<ItemDiscount> discounts) {
        this.discounts = discounts;
    }

    private String lista_primenjenih_popusta;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void addItemDiscount(ItemDiscount itemDiscount){
        if (this.discounts == null) {

            this.discounts = new HashSet<ItemDiscount>();

        }
        this.discounts.add(itemDiscount);
        this.setDiscounts(this.discounts);
        System.err.print("a");
    }

    public void countPrice(){
        System.err.print("kita");
        Double currPrice = this.getItem_quantity()*this.getItem().getPrice();
        this.final_price = currPrice - ((currPrice*this.getDiscount_percent())/100);
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public Double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(Double final_price) {
        this.final_price = final_price;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getLista_primenjenih_popusta() {
        return lista_primenjenih_popusta;
    }

    public void setLista_primenjenih_popusta(String lista_primenjenih_popusta) {
        this.lista_primenjenih_popusta = lista_primenjenih_popusta;
    }
}
