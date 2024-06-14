package dao.cart;

import bean.product.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CartDAOImplTest {

    private CartDAO cartDAO;

    @Before
    public void setUp() throws Exception {
        cartDAO = new CartDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void test_getProduct() {
        List<Product> productList = cartDAO.getProduct(2);
        List<Integer> exceptIdList = new ArrayList<>();
        List<Integer> actualIdList = new ArrayList<>();
        exceptIdList.add(3);
        exceptIdList.add(80);
        exceptIdList.add(102);
        for(Product p : productList)
            actualIdList.add(p.getId());

        assertArrayEquals(exceptIdList.toArray(),actualIdList.toArray());
    }

    @Test
    public void insert() {
        cartDAO.insert(2,90,3);
        List<Integer> exceptIdList = new ArrayList<>();
        List<Integer> actualIdList = new ArrayList<>();
        exceptIdList.add(3);
        exceptIdList.add(80);
        exceptIdList.add(90);
        exceptIdList.add(102);
        List<Product> productList = cartDAO.getProduct(2);
        for(Product p : productList)
            actualIdList.add(p.getId());
        assertArrayEquals(exceptIdList.toArray(),actualIdList.toArray());
    }

    @Test
    public void delete() {

        cartDAO.deleteProduct(2,3);
        List<Integer> exceptIdList = new ArrayList<>();
        List<Integer> actualIdList = new ArrayList<>();
        exceptIdList.add(80);
        exceptIdList.add(102);
        List<Product> productList = cartDAO.getProduct(2);
        for(Product p : productList)
            actualIdList.add(p.getId());
        assertArrayEquals(exceptIdList.toArray(),actualIdList.toArray());
    }

    @Test
    public void updateQuantity() {
        cartDAO.updateQuantity(2,102,3);
        List<Product> productList = cartDAO.getProduct(2);
        for(Product p : productList) {
            if(p.getId() == 102)
                assertEquals(Integer.valueOf(3),p.getQuantity());
        }

    }
}