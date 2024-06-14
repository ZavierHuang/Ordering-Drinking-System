package service.productService;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ProductServiceImplTest {
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getProductsByType() {
        List<Product> productList = productService.getProductsByType("Boutique hand coffee");
        assertEquals(7,productList.size());
        for(Product p:productList)
            assertEquals("Boutique hand coffee",p.getType());
    }

    @Test
    public void test_getAllType() {
        List<Product> typeList = productService.getAllType();
        List<String> exceptList = new ArrayList<>();
        exceptList.add("Boutique hand coffee");
        exceptList.add("Chocolate");
        exceptList.add("Tea");
        exceptList.add("Milk");
        exceptList.add("Coffee");
        exceptList.add("Latte");
        List<String> actualList = new ArrayList<>();
        for (Product type : typeList) {
            actualList.add(type.getType());
        }
        assertArrayEquals(exceptList.toArray(),actualList.toArray());
    }
}