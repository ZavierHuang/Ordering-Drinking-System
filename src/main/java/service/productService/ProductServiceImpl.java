package service.productService;

import bean.product.Product;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();
    List<Product> productList = null;
    @Override
    public List<Product> getProductsByType(String type){
        if (type.equals("Milk")) {
            productList = productDAO.getMilk();
        } else if (type.equals("Boutique hand coffee")) {
            productList = productDAO.getBoutique();
        } else if (type.equals("Latte")) {
            productList = productDAO.getLatte();
        } else if (type.equals("Tea")) {
            productList = productDAO.getTea();
        } else if (type.equals("Chocolate")) {
            productList = productDAO.getChocolate();
        } else if (type.equals("Coffee")) {
            productList = productDAO.getCoffee();
        }
        return productList;
    }

    @Override
    public List<Product> getAllType(){
        List<Product> typeList = productDAO.getType();
        return typeList;
    }
}
