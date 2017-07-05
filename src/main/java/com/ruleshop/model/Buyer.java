package com.ruleshop.model;

import javax.persistence.*;

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

    private String points;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
