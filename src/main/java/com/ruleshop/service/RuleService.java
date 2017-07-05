package com.ruleshop.service;

import com.ruleshop.model.User;

/**
 * Created by milosandric on 05.07.17.
 */
public interface RuleService {
    public User findUser(String email);
}
