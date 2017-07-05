package com.ruleshop.DAOImplementation;

import com.ruleshop.DAO.UserDAO;
import com.ruleshop.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by milosandric on 05.07.17.
 */
@Repository
public class UserDAOImplementation implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUser(String email) {
        System.err.print("aa"+email);
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.email=:email");
        query.setParameter("email",email);
        List<User> userList = query.list();
        User user = null;
        for(User a:userList){
            user = a;
        }

        return user;
    }
}
