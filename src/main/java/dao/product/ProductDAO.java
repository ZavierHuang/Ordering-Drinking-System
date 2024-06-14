package dao.product;

import bean.menuItem.MenuItem;
import bean.product.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getProductsByType(String productType);
    public List<Product> getMilk();
    public List<Product> getBoutique();
    public List<Product> getLatte();
    public List<Product> getTea();
    public List<Product> getChocolate();
    public List<Product> getCoffee();
    public Product getAddInfo(int ProductID);
    public Integer getProductID(Product product);
    public List<Product> getType();
    public List<Product> getAllProducts();
    public void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem);
    public void addNewMenuItem(MenuItem newMenuItem);
    public List<MenuItem> searchMenuItem(MenuItem searchMenuItem, String symbol);
    void deleteMenuItemByName(String menuItemName);
    void updateStack(Integer orderID);
}
