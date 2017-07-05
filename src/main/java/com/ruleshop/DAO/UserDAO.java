package com.ruleshop.DAO;

import com.ruleshop.model.User;

/**
 * Created by milosandric on 05.07.17.
 */
public interface UserDAO {
    public User findUser(String email);
}
