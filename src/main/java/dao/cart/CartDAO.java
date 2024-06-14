package dao.cart;

import bean.product.Product;

import java.util.List;

public interface CartDAO {
    public void insert(int orderID, int productID, int quantity);
    public void deleteProduct(int orderID, int productID);
    public List<Product> getProduct(int orderID);
    public Integer ProductExist(int orderID, int productID);
    public void updateQuantity(int orderID, int productID, int Quantity);
}
