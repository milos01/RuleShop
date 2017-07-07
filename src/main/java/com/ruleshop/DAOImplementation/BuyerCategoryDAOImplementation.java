package com.ruleshop.DAOImplementation;

import com.ruleshop.DAO.BuyerCategoryDAO;
import com.ruleshop.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milosandric on 06.07.17.
 */
@Repository
public class BuyerCategoryDAOImplementation implements BuyerCategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<BuyerCategory> getAllBCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from BuyerCategory");
        List<BuyerCategory> list=query.list();
        return list;
    }

    @Override
    public void addCategory(BuyerCategory category_name) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(category_name);
    }

    @Override
    public void addLimit(CategoryLimit limit) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(limit);
    }

    @Override
    public BuyerCategory findCactegory(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from BuyerCategory bc where bc.id=:id");
        query.setParameter("id",category_id);
        List<BuyerCategory> bcList = query.list();
        BuyerCategory b = null;
        for(BuyerCategory a:bcList){
            b = a;
        }

        return b;
    }

    @Override
    public CategoryLimit findLimit(int limit_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from CategoryLimit cl where cl.id=:id");
        query.setParameter("id",limit_id);
        List<CategoryLimit> clList = query.list();
        CategoryLimit c = null;
        for(CategoryLimit a:clList){
            c = a;
        }

        return c;
    }

    @Override
    public void updateLimit(CategoryLimit cl) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(cl);
    }

    @Override
    public void updateCategory(BuyerCategory bc) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(bc);
    }

    @Override
    public void addItemCategory(ItemCategory ic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(ic);
    }

    @Override
    public List<ItemCategory> getAllICategories() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from ItemCategory");
        List<ItemCategory> list=query.list();
        return list;
    }

    @Override
    public ItemCategory findItemCategory(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from ItemCategory ic where ic.id=:id");
        query.setParameter("id",category_id);
        List<ItemCategory> clList = query.list();
        ItemCategory c = null;
        for(ItemCategory a:clList){
            c = a;
        }

        return c;
    }

    @Override
    public void updateItemCategory(ItemCategory ic) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(ic);
    }

    @Override
    public Sale addNewSale(Sale sale) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(sale);
        return sale;
    }

    @Override
    public List<Sale> getAllSales() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Sale");
        List<Sale> list=query.list();
        return list;
    }

    @Override
    public Sale findSale(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Sale s where s.id=:id");
        query.setParameter("id",category_id);
        List<Sale> saleList = query.list();
        Sale c = null;
        for(Sale a:saleList){
            c = a;
        }

        return c;
    }

    @Override
    public Sale updateSale(Sale sale) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(sale);
        return sale;
    }

    @Override
    public List<Item> getItemsForOrder() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Item it where it.needOrder =:needOrder");
        query.setParameter("needOrder", true);
        List<Item> list=query.list();
        return list;
    }

    @Override
    public Item findItem(int item_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Item s where s.id=:id");
        query.setParameter("id", item_id);
        List<Item> saleList = query.list();
        Item c = null;
        for(Item a:saleList){
            c = a;
        }

        return c;
    }

    @Override
    public void updateItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(item);
    }
}
