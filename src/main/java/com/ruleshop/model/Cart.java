package com.ruleshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by milosandric on 09.07.17.
 */
@Entity
@Table(name="item_user")
public class Cart implements Serializable {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;


    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
