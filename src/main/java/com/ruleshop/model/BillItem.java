package com.ruleshop.model;

import javax.persistence.*;

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

    private String item_name;

    private Double item_price;

    private int item_quantity;

    private int discount_percent;

    private Double final_price;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(nullable=false, name = "bill_id")
    private Bill bill;

    private String lista_primenjenih_popusta;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
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
