package dao.orders;

import bean.orders.Orders;
import bean.product.Product;

import java.util.List;

public interface OrdersDAO {
    public List<Orders> getCustomerOrdersByID(int customerID);
    public List<Product> getCustomerOrdersProductByDateAndID(int customerID, String date);
    public List<Orders> getAllOrdersDetail();
    public Integer initial();
    public void update(int orderID, String dates, int amount, int status);
    public Boolean updateOrderStatus(int order,int status);

    Integer getOrderStatusByAcc(String acc, String date);

    Integer cancelOrderByAcc(String account, String orderDate);
}
