package dao.orders;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.orders.Orders;
import bean.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    private DBConnection dbConnection = new DBConnectionImpl();
//    private static final String GET_ORDER = "SELECT * FROM orders JOIN build ON build.orderID = orders.orderID WHERE UserID = ? AND Status = 0 ORDER BY dates ASC";
    private static final String GET_ORDER = "SELECT * FROM orders inner JOIN build ON build.orderID = orders.orderID WHERE UserID = ? and orders.Dates is not null ORDER BY dates DESC;";
    private static final String INITIAL_ORDER = "INSERT INTO orders(status) VALUES (?)";
    private static final String GET_ORDER_STATUS = "SELECT * FROM user u\n" +
                                                    "inner join build b on u.UserID=b.UserID\n" +
                                                    "inner join orders o on b.OrderID=o.OrderID\n" +
                                                    "where u.Account=? and o.Dates=? and u.Level>=0 and u.Level<10;";
    private static final String GET_PRODUCT = "SELECT addCart.Quantity, product.*\n" +
                                              "FROM addCart\n" +
                                              "JOIN product ON addCart.productID = product.ProductID\n" +
                                              "JOIN orders ON addCart.orderID = orders.orderID\n" +
                                              "JOIN build ON build.orderID = orders.OrderID\n" +
                                              "JOIN user ON build.UserID = user.UserID\n" +
                                              "WHERE build.UserID = ? AND dates = ?;\n";
    private static final String GET_MAX_ORDERID = "SELECT MAX(orderID) FROM orders";
    private static final String INSERT_ORDER = "INSERT INTO orders(customerID, productID, dates, amount, quantity) VALUES (?,?,?,?,?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET dates = ?, amount = ?, status = ? WHERE orderID = ?";

    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status=? WHERE orderid=?";

    private static final String DELETE_ORDER = "UPDATE orders SET Status = ?  WHERE Dates = ? ;";

    private static final String GET_ALL_ORDERS = "SELECT user.Account, build.userId,orders.orderId,orders.Dates,orders.Amount,orders.status\n" +
            "FROM orders \n" +
            "INNER JOIN build ON orders.orderId = build.orderId \n" +
            "INNER JOIN user ON build.userId = user.userId \n" +
            "WHERE orders.Dates is not null\n" +
            "ORDER BY \n" +
            "    CASE orders.status\n" +
            "        WHEN 0 THEN 1\n" +
            "        WHEN 1 THEN 2\n" +
            "        WHEN -1 THEN 3\n" +
            "        WHEN 2 THEN 4\n" +
            "    END;";

    @Override
    public List<Orders> getCustomerOrdersByID(int customerID){
        Connection connection = dbConnection.getConnection();
        List<Orders> ordersList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)){
            preparedStatement.setInt(1, customerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Orders orders = new Orders();
                    orders.setOrderID(resultSet.getInt("orderId"));
                    orders.setDates(resultSet.getString("dates"));
                    orders.setAmount(resultSet.getInt("amount"));
                    ordersList.add(orders);
                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public List<Orders> getAllOrdersDetail(){
        Connection connection = dbConnection.getConnection();
        List<Orders> ordersList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS)){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Orders orders = new Orders();
                    orders.setOrderID(resultSet.getInt("orderId"));
                    orders.setCustomerID(resultSet.getInt("userId"));
                    orders.setCustomerName(resultSet.getString("account"));
                    orders.setDates(resultSet.getString("dates"));
                    orders.setAmount(resultSet.getInt("amount"));
                    orders.setStatus(resultSet.getInt("status"));
                    orders.setStatusString();

                    ordersList.add(orders);
                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public List<Product> getCustomerOrdersProductByDateAndID(int userID, String date){
        Connection connection = dbConnection.getConnection();
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT)){
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Product product = new Product();
                    product.setId(resultSet.getInt("productId"));
                    product.setName(resultSet.getString("ProductName"));
                    product.setIce(resultSet.getString("ice"));
                    product.setSize(resultSet.getString("size"));
                    product.setSugar(resultSet.getString("sugar"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setPrice(resultSet.getInt("price"));
//                    product.setStatus(resultSet.getString("Status"));
                    productList.add(product);
                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Integer initial(){
        Connection connection = dbConnection.getConnection();
        Integer orderID = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INITIAL_ORDER);
            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_MAX_ORDERID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            orderID = resultSet.getInt(1);

            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return orderID;
    }

    public void update(int orderID, String dates, int amount, int status){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)){
            preparedStatement.setString(1,dates);
            preparedStatement.setInt(2,amount);
            preparedStatement.setInt(3,status);
            preparedStatement.setInt(4,orderID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean updateOrderStatus(int orderId,int status) {
        Connection connection=dbConnection.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_ORDER_STATUS)){
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer getOrderStatusByAcc(String acc, String date) {

        Connection connection = dbConnection.getConnection();
        Integer Status = null;
        try {

            //需要增加時間查狀態
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_STATUS);
            preparedStatement.setString(1, acc);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

//            Status = resultSet.getInt("Status")+"";
            Status = resultSet.getInt("Status");

            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Status;
    }

    @Override
    public Integer cancelOrderByAcc(String account, String orderDate) {
        Connection connection = dbConnection.getConnection();
        Integer status = getOrderStatusByAcc(account, orderDate);

        switch (status){
            case 0:
                try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)){
                    preparedStatement.setString(1,"-1");
                    preparedStatement.setString(2,orderDate);
//                    preparedStatement.setString(3,account);

                    preparedStatement.executeUpdate();
                    connection.close();
                    return 0;
//                    return "OK";
                }catch (SQLException e) {
                    return -1;
                }
            default:
                return -2;

        }

    }
}
