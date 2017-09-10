package com.ruleshop.DAOImplementation;

import com.ruleshop.DAO.BuyerCategoryDAO;
import com.ruleshop.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Override
    public List<Bill> getAllBills() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b");
        List<Bill> list=query.list();
        return list;
    }

    @Override
    public List<Bill> getOrderedBills() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b where b.state=:state");
        query.setParameter("state", "porucen");
        List<Bill> saleList = query.list();

        return saleList;
    }

    @Override
    public List<Bill> getRejectedBills() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b where b.state=:state");
        query.setParameter("state", "odbijen");
        List<Bill> saleList = query.list();

        return saleList;
    }

    @Override
    public Bill findBill(int bill_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b where b.id=:id");
        query.setParameter("id", bill_id);
        List<Bill> saleList = query.list();
        Bill c = null;
        for(Bill a:saleList){
            c = a;
        }

        return c;
    }

    @Override
    public Item findBillByName(String item_name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Item i where i.name=:name");
        query.setParameter("name", item_name);
        List<Item> saleList = query.list();
        Item c = null;
        for(Item a:saleList){
            c = a;
        }

        return c;
    }

    @Override
    public void updateBill(Bill bill) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(bill);
    }



    @Override
    public List<Item> getAllSICategories(String searchCode, String searchName, String category, Double price_from, Double price_to) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Item i where i.code=:searchCode and i.name=:searchName and i.itemCategory.name=:category  and i.price>:price_from and i.price<:price_to");
//        if (!searchCode.equals("") && searchName.equals("") && category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.code=:searchCode");
//        }else if (searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.name=:searchName");
//        }else if (searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.itemCategory.name=:category");
//        }else if (searchCode.equals("") && searchName.equals("") && category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.price>:price_from");
//        }else if (searchCode.equals("") && searchName.equals("") && category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where  i.price<:price_from");
//        }else if (!searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.code=:searchCode and i.name=:searchName");
//        }else if (!searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.code=:searchCode and i.itemCategory.name=:category");
//        } else if (!searchCode.equals("") && searchName.equals("") && category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where  i.code=:searchCode and i.price>:price_from");
//        } else if (!searchCode.equals("") && searchName.equals("") && category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where  i.code=:searchCode and i.price<:price_from");
//        }else if (searchCode.equals("") && !searchName.equals("") && !category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where i.name=:searchName and i.itemCategory.name=:category");
//        }else if (searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where i.name=:searchName and i.price>:price_from");
//        }else if (searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where i.name=:searchName and i.price<:price_from");
//        }else if (searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where i.itemCategory.name=:category and i.price>:price_from");
//        }else if (searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where i.itemCategory.name=:category and i.price<:price_from");
//        }else if (searchCode.equals("") && searchName.equals("") && category.equals("") && price_from != null && price_to != null){
//            Query query=session.createQuery("from Item i where i.price>:price_from and i.price<:price_from");
//        }else if (!searchCode.equals("") && !searchName.equals("") && !category.equals("") && price_from == null && price_to == null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.name=:searchName and i.itemCategory.name=:category");
//        }else if (!searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.name=:searchName and i.price>:price_from");
//        }else if (!searchCode.equals("") && !searchName.equals("") && category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.name=:searchName and i.price<:price_from");
//        }else if (!searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from != null && price_to == null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.itemCategory.name=:category and i.price>:price_from");
//        }else if (!searchCode.equals("") && searchName.equals("") && !category.equals("") && price_from == null && price_to != null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.itemCategory.name=:category and i.price<:price_from");
//        }else if (!searchCode.equals("") && searchName.equals("") && category.equals("") && price_from != null && price_to != null){
//            Query query=session.createQuery("from Item i where i.code=:searchCode and i.price>:price_from and i.price<:price_from");
//        }








//             Query query=session.createQuery("from Item i where i.name =:searchName and i.code=:searchCode");
        query.setParameter("searchName", searchName);
        query.setParameter("searchCode", searchCode);
        query.setParameter("category", category);
        query.setParameter("price_from", price_from);
        query.setParameter("price_to", price_to);

        List<Item> saleList = query.list();

        return saleList;
    }

    @Override
    public List<Item> getAllItems() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Item i");
        List<Item> saleList = query.list();

        return saleList;
    }

    @Override
    public void addCartItem(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cart);
    }

    @Override
    public List<Cart> getUserCartItems(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Cart c where c.user.id=:id");
        query.setParameter("id", id);
        List<Cart> saleList = query.list();

        return saleList;
    }

    @Override
    public void addItemDiscount(ItemDiscount id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(id);
    }

    @Override
    public void addBillItem(BillItem bitem) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(bitem);
    }

    @Override
    public void addBillDiscount(BillDiscount billDiscount) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(billDiscount);
    }

    @Override
    public Bill addBill(Bill bill) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(bill);
        return bill;
    }

    @Override
    public void updateBillItem(BillItem bill_item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(bill_item);
    }

    @Override
    public List<Bill> findByDateAfterAndCustomer(Date date, int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b where b.buyer.id=:id and b.ceated_at > :time");
        query.setParameter("id", id);
        query.setParameter("time", date);
        List<Bill> saleList = query.list();

        return saleList;
    }

    @Override
    public List<Sale> findByDateEndingAfter(Date date) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Sale s where s.sale_from < :now and s.sale_to > :now");

        query.setParameter("now", date);
        List<Sale> saleList = query.list();

        return saleList;
    }

    @Override
    public List<Bill> getSuccessBills() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Bill b where b.state=:state");
        query.setParameter("state", "uspesno_realizovan");
        List<Bill> saleList = query.list();

        return saleList;
    }


}
