package service.ordersService;

import bean.orders.Orders;
import bean.product.Product;

import java.util.List;

public interface OrdersService {
    public List<Product> getOrderProductsByDate(String account,String date);
    public List<Orders> getOrder(String account);
    public void buildOrder(String account, Integer amount);

    public List<Orders> getAllOrdersDetail();

    public Boolean updateOrderStatus(int order,int status);

    Integer getOrderStatusByAcc(String acc, String date);

    Integer cancelOrderByAcc(String account, String orderDate);
}
