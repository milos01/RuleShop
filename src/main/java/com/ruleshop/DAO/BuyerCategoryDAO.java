package com.ruleshop.DAO;

import com.ruleshop.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milosandric on 06.07.17.
 */
public interface BuyerCategoryDAO {
    public List<BuyerCategory> getAllBCategories();

    void addCategory(BuyerCategory category_name);

    void addLimit(CategoryLimit limit);

    BuyerCategory findCactegory(int category_id);

    CategoryLimit findLimit(int limit_id);

    void updateLimit(CategoryLimit cl);

    void updateCategory(BuyerCategory bc);

    void addItemCategory(ItemCategory ic);

    List<ItemCategory> getAllICategories();

    ItemCategory findItemCategory(int category_id);

    void updateItemCategory(ItemCategory ic);

    Sale addNewSale(Sale sale);

    List<Sale> getAllSales();

    Sale findSale(int category_id);

    Sale updateSale(Sale sale);

    List<Item> getItemsForOrder();

    Item findItem(int item_id);

    void updateItem(Item item);
}
