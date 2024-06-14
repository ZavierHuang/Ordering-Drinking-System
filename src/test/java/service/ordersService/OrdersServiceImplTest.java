package service.ordersService;

import bean.orders.Orders;
import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OrdersServiceImplTest {
    private OrdersService ordersService;

    @Before
    public void setUp() throws Exception {
        ordersService = new OrdersServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrderStatusByAcc() {
        Integer status = ordersService.getOrderStatusByAcc("111", "2024-06-12 14:22:58");
        assertEquals(Integer.valueOf(0),status);
    }

    @Test
    public void test_cancelOrderByAcc() {
        Integer status = ordersService.cancelOrderByAcc("111", "2024-06-12 14:22:58");
        assertEquals(Integer.valueOf(0),status);
    }

    @Test
    public void test_getOrder() {
        List<Orders> ordersList = ordersService.getOrder("111");
        assertEquals(1,ordersList.size());
        assertEquals(Integer.valueOf(1),ordersList.get(0).getOrderID());
        assertEquals("2024-06-12 14:22:58",ordersList.get(0).getDates());
        assertEquals(Integer.valueOf(210),ordersList.get(0).getAmount());
    }

    @Test
    public void test_getOrderProductsByDate() {
        List<Product> productList = ordersService.getOrderProductsByDate("111", "2024-06-12 14:22:58");
        assertEquals(3,productList.size());
        List<Integer> actualList = new ArrayList<>();
        List<Integer> exceptList = new ArrayList<>();
        exceptList.add(1);
        exceptList.add(25);
        exceptList.add(49);
        for(Product p : productList)
            actualList.add(p.getId());
        assertArrayEquals(exceptList.toArray(),actualList.toArray());
    }

    @Test
    public void test_getAllOrdersDetail() {
        List<Orders> exceptList = ordersService.getAllOrdersDetail();
        assertEquals(1,exceptList.size());
        assertEquals(Integer.valueOf(1), exceptList.get(0).getOrderID());
        assertEquals(Integer.valueOf(210), exceptList.get(0).getAmount());
        assertEquals(Integer.valueOf(0), exceptList.get(0).getStatus());
    }

}