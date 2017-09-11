package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by milosandric on 08.07.17.
 */

@Entity
@Table(name="bills")
public class Bill {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date ceated_at;

    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "buyer_id")
    private Buyer buyer;

    private String state;

    private Double origina_price;

    private int discount_percent;

    private Double final_price;

    private double spent_points;

    private int ganed_points;



    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "bill")
    public Set<BillItem> bill_items;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "bill")
    private Set<BillDiscount> discounts;

    public void addBillDiscount(BillDiscount billDiscount){
        if (this.discounts == null) {

            this.discounts = new HashSet<BillDiscount>();

        }
        this.discounts.add(billDiscount);
        this.setDiscounts(this.discounts);
    }


    public void addBonusPointsToUser(int bonusDiscount) {

        setGaned_points((int) ((final_price*bonusDiscount)/100));
        this.buyer.setPoints(buyer.getPoints() + ((int) ((final_price*bonusDiscount))/100));
    }

    public void applyDiscount(){
        this.final_price = this.origina_price - ((this.origina_price * this.discount_percent)/100);
//        if (buyer.getPoints() < spent_points) {
//            spent_points = buyer.getPoints();
//        }
//        this.final_price -= spent_points;
//        buyer.setPoints(buyer.getPoints() - spent_points);
    }


    public void addGeneralDiscount(BillDiscount generalDiscount) {
        this.discounts.add(generalDiscount);
    }

    public Set<BillDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<BillDiscount> discounts) {
        this.discounts = discounts;
    }

    public Set<BillItem> getBill_items() {
        return bill_items;
    }

    public void setBill_items(Set<BillItem> bill_items) {
        this.bill_items = bill_items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCeated_at() {
        return ceated_at;
    }

    public void setCeated_at(Date ceated_at) {
        this.ceated_at = ceated_at;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getOrigina_price() {
        return origina_price;
    }

    public void setOrigina_price(Double origina_price) {
        this.origina_price = origina_price;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) { this.discount_percent = discount_percent;
    }

    public Double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(Double final_price) {

        System.err.print(final_price);
        this.final_price = final_price;
    }

    public double getSpent_points() {
        return spent_points;
    }

    public void setSpent_points(double spent_points) {
        this.spent_points = spent_points;
    }

    public int getGaned_points() {
        return ganed_points;
    }

    public void setGaned_points(int ganed_points) {
        this.ganed_points = ganed_points;
    }


    @Override
    public String toString() {
        return "Bill{" +
                "discounts=" + discounts +
                '}';
    }
}
