package service.cartService;

import bean.product.Product;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class CartServiceImplTest {

    private ProductDAO productDAO;
    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        productDAO = new ProductDAOImpl();
        cartService = new CartServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getCustomerCart(){
        List<Product> productList = cartService.getCustomerCart("111");
        List<Integer> actualList = new ArrayList<>();
        List<Integer> exceptList = new ArrayList<>();
        exceptList.add(3);
        exceptList.add(80);
        exceptList.add(102);
        for(Product p : productList)
            actualList.add(p.getId());
        assertArrayEquals(actualList.toArray(),exceptList.toArray());
    }
    @Test
    public void test_addProduct() {
        Product product = productDAO.getAddInfo(1);
        product.setQuantity(2);
        cartService.addProduct("111",product);
        List<Product> productList = cartService.getCustomerCart("111");
        List<Integer> actualList = new ArrayList<>();
        List<Integer> exceptList = new ArrayList<>();
        exceptList.add(1);
        exceptList.add(3);
        exceptList.add(80);
        exceptList.add(102);
        for(Product p : productList)
            actualList.add(p.getId());
        assertArrayEquals(actualList.toArray(),exceptList.toArray());
    }

    @Test
    public void test_deleteProduct(){
        cartService.deleteProduct("111",3);
        List<Product> productList = cartService.getCustomerCart("111");
        List<Integer> actualList = new ArrayList<>();
        List<Integer> exceptList = new ArrayList<>();
        exceptList.add(80);
        exceptList.add(102);
        for(Product p : productList)
            actualList.add(p.getId());
        assertArrayEquals(actualList.toArray(),exceptList.toArray());
    }


}