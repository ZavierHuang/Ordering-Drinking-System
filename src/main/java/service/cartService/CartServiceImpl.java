package service.cartService;

import bean.product.Product;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.cart.CartDAO;
import dao.cart.CartDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import java.util.List;

public class CartServiceImpl implements CartService{
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();

    @Override
    public void addProduct(String account, Product product){
        Integer productID = productDAO.getProductID(product);
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getCustomerCartID(customerID);
        Integer currentQuantity = cartDAO.ProductExist(orderID, productID);
        if (currentQuantity != null) {
            cartDAO.updateQuantity(orderID, productID, product.getQuantity() + currentQuantity);
        } else{
            cartDAO.insert(orderID, productID, product.getQuantity());
        }
    }

    @Override
    public void deleteProduct(String account, Integer productID){
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getCustomerCartID(customerID);
        cartDAO.deleteProduct(orderID, productID);
    }

    @Override
    public List<Product> getCustomerCart(String account){
        Integer userId = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getCustomerCartID(userId);
        System.out.println("userId:"+userId+" orderId:"+orderID);
        return cartDAO.getProduct(orderID);
    }
}
