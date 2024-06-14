package dao.user.orders;

import bean.orders.Orders;
import bean.product.Product;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OrdersDAOImplTest {

    private OrdersDAO ordersDAO;
    @Before
    public void setUp() throws Exception {
        ordersDAO = new OrdersDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getCustomerOrdersByID() {
        List<Orders> orders = ordersDAO.getCustomerOrdersByID(1);
        assertEquals(1,orders.size());
        assertEquals(Integer.valueOf(1), orders.get(0).getOrderID());
    }

    @Test
    public void test_getAllOrdersDetail(){
        List<Orders> exceptList = ordersDAO.getAllOrdersDetail();
        assertEquals(1,exceptList.size());
        assertEquals(Integer.valueOf(1), exceptList.get(0).getOrderID());
        assertEquals(Integer.valueOf(210), exceptList.get(0).getAmount());
        assertEquals(Integer.valueOf(0), exceptList.get(0).getStatus());
    }

    @Test
    public void test_getCustomerOrdersProductByDateAndID(){
        int customerID = 1;
        String date = "2024-06-12 14:22:58";
        List<Product> productList = ordersDAO.getCustomerOrdersProductByDateAndID(customerID,date);
        assertEquals(3,productList.size());
        List<Integer> actualList = new ArrayList<>();
        List<Integer> exceptList = new ArrayList<>();
        exceptList.add(1);
        exceptList.add(25);
        exceptList.add(49);
        for(Product p :productList)
            actualList.add(p.getId());

        assertArrayEquals(exceptList.toArray(),actualList.toArray());

    }
}