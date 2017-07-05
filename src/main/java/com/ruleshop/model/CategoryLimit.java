package com.ruleshop.model;

import javax.persistence.*;

/**
 * Created by milosandric on 05.07.17.
 */
@Entity
@Table(name="category_limits")
public class CategoryLimit {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int limit_from;

    private int limit_to;

    private int point_percent;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BuyerCategory buyerCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit_from() {
        return limit_from;
    }

    public void setLimit_from(int limit_from) {
        this.limit_from = limit_from;
    }

    public int getLimit_to() {
        return limit_to;
    }

    public void setLimit_to(int limit_to) {
        this.limit_to = limit_to;
    }

    public int getPoint_percent() {
        return point_percent;
    }

    public void setPoint_percent(int point_percent) {
        this.point_percent = point_percent;
    }

    public BuyerCategory getBuyerCategory() {
        return buyerCategory;
    }

    public void setBuyerCategory(BuyerCategory buyerCategory) {
        this.buyerCategory = buyerCategory;
    }
}
