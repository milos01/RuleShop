package com.ruleshop.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by milosandric on 05.07.17.
 */
@Entity
@Table(name="buyer_categories")
public class BuyerCategory {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category_name;

    @OneToMany(mappedBy = "buyerCategory", cascade = CascadeType.ALL)
    private Set<CategoryLimit> limits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Set<CategoryLimit> getLimits() {
        return limits;
    }

    public void setLimits(Set<CategoryLimit> limits) {
        this.limits = limits;
    }
}
