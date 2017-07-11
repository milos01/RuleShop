package com.ruleshop.model;

import javax.persistence.*;

/**
 * Created by milosandric on 11.07.17.
 */
@Entity
@Table(name="item_discount")
public class ItemDiscount {
    public enum discount_type {
        BASE_DISCOUNT, ADDITIONAL_DISCOUNT
    };

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "billItem_id")
    private BillItem billItem;

    private int discount_percent;

    private discount_type dt;

    public int getId() {
        return id;
    }

    public ItemDiscount() {
    }

    public ItemDiscount(BillItem billItem, int discount_percent, discount_type dt) {
        this.code = "code:";
        this.billItem = billItem;
        this.discount_percent = discount_percent;
        this.dt = dt;
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

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public discount_type getDt() {
        return dt;
    }

    public void setDt(discount_type dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ItemDiscount{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount_percent=" + discount_percent +
                ", dt=" + dt +
                '}';
    }
}
