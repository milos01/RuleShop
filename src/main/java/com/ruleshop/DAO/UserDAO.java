package com.ruleshop.DAO;

import com.ruleshop.model.BillItem;
import com.ruleshop.model.Buyer;
import com.ruleshop.model.Role;
import com.ruleshop.model.User;

/**
 * Created by milosandric on 05.07.17.
 */
public interface UserDAO {
    public User findUser(String email);


    Role findRole(int i);

    void addUser(User user);

    void addBuyer(Buyer buyer);
}
