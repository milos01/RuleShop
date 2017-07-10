package com.ruleshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by milosandric on 05.07.17.
 */

@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = -2184795033586333876L;

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    @ManyToOne(targetEntity = Role.class,fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Buyer buyer;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Cart> cartItems;

    public Set<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Boolean hasRole(String rName){
        if(this.role.getRole_name().equals(rName)){
            return true;
        }
        return false;

    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
