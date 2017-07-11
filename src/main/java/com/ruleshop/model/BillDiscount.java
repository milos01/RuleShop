package com.ruleshop.model;

import javax.persistence.*;

/**
 * Created by milosandric on 11.07.17.
 */
@Entity
@Table(name="bill_discount")
public class BillDiscount {
    public enum discount_type {
        BASE_DISCOUNT, ADDITIONAL_DISCOUNT
    };

    public BillDiscount() {
    }

    public BillDiscount(Bill bill, int discount_percent, BillDiscount.discount_type dt) {
        this.code = "code"+bill.getId();
        this.bill = bill;
        this.discount_percent = discount_percent;
        this.dt = dt;
    }

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "bill_id")
    private Bill bill;

    private int discount_percent;

    private discount_type dt;

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

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
}
