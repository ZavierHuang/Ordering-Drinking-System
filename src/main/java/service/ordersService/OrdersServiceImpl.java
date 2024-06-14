package service.ordersService;

import bean.orders.Orders;
import bean.product.Product;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class  OrdersServiceImpl implements OrdersService{
    private final UserDAO userDAO = new UserDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();


    @Override
    public List<Product> getOrderProductsByDate(String account ,String date){
        Integer customerID = userDAO.getCustomerID(account);
        return ordersDAO.getCustomerOrdersProductByDateAndID(customerID, date);
    }

    @Override
    public List<Orders> getOrder(String account){
        Integer customerID = userDAO.getCustomerID(account);
        return ordersDAO.getCustomerOrdersByID(customerID);
    }



    @Override
    public void buildOrder(String account, Integer amount){
        Integer customerID = userDAO.getCustomerID(account);
        Integer orderID = buildDAO.getOrderID(customerID, 1).get(0);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = dateFormat.format(now);

        ordersDAO.update(orderID, dates, amount,0);
        orderID = ordersDAO.initial();
        buildDAO.insert(customerID, orderID);
        productDAO.updateStack(orderID-1);
    }
    @Override
    public List<Orders> getAllOrdersDetail(){
        return ordersDAO.getAllOrdersDetail();
    }
    @Override
    public Boolean updateOrderStatus(int order,int status){
        return ordersDAO.updateOrderStatus(order,status);
    }

    @Override
    public Integer getOrderStatusByAcc(String acc, String date) {
        return ordersDAO.getOrderStatusByAcc(acc,date);
    }

    @Override
    public Integer cancelOrderByAcc(String account, String orderDate) {
        return ordersDAO.cancelOrderByAcc(account,orderDate);
    }
}
