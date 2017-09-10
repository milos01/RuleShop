package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by milosandric on 05.07.17.
 */
@Entity
@Table(name="buyers")
public class Buyer {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private int address_no;

    private double points;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "category_id")
    BuyerCategory buyerCategory;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer")
    private Set<Bill> bills;

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public BuyerCategory getBuyerCategory() {
        return buyerCategory;
    }

    public void setBuyerCategory(BuyerCategory buyerCategory) {
        this.buyerCategory = buyerCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddress_no() {
        return address_no;
    }

    public void setAddress_no(int address_no) {
        this.address_no = address_no;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
