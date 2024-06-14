package service.cartService;

import bean.product.Product;

import java.util.List;

public interface CartService {
    public void addProduct(String account, Product product);
    public void deleteProduct(String account, Integer productID);
    public List<Product> getCustomerCart(String account);

}
