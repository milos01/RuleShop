package com.ruleshop.service;

import com.ruleshop.DAO.UserDAO;
import com.ruleshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by milosandric on 05.07.17.
 */
@Service
public class RuleServiceImplementation implements RuleService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User findUser(String email) {
        return this.userDAO.findUser(email);
    }
}
