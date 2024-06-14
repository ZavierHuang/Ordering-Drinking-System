package dao.user.product;

import bean.product.Product;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ProductDAOImplTest {
    private ProductDAO productDAO;
    @Before
    public void setUp() throws Exception {
        productDAO = new ProductDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getAddInfo() {
        Product product = productDAO.getAddInfo(95);
        assertEquals("黃金曼特寧",product.getName());
    }

    @Test
    public void test_getProductID() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setIce("熱");
        product.setSize("M");
        product.setSugar("無糖");
        product.setPrice(70);
        Integer ProductID = productDAO.getProductID(product);
        assertEquals((Integer)2,ProductID);
    }

    @Test
    public void test_getType() {
        List<Product> typeList = productDAO.getType();
        List<String> actualList = new ArrayList<>();
        List<String> exceptList = new ArrayList<>();
        exceptList.add("Boutique hand coffee");
        exceptList.add("Chocolate");
        exceptList.add("Tea");
        exceptList.add("Milk");
        exceptList.add("Coffee");
        exceptList.add("Latte");
        for(int i = 0; i < typeList.size(); i++){
            Product product = typeList.get(i);
            actualList.add(product.getType());
        }
        assertArrayEquals(actualList.toArray(),exceptList.toArray());
    }

    @Test
    public void test_getAllProducts(){
        assertEquals(72, productDAO.getAllProducts().size());
    }
}