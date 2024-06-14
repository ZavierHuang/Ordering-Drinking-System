package dao.cart;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO{

    private final DBConnection dbConnection = new DBConnectionImpl();
    private static final String INSERT_CART = "INSERT INTO addCart(orderID, productID, quantity) VALUES (?,?,?)";
    private static final String DELETE_PRODUCT = "DELETE FROM addCart WHERE orderID = ? AND productID = ?";
    private static final String GET_PRODUCT = "SELECT product.*, addCart.Quantity FROM addCart JOIN product ON addCart.productID = product.productID WHERE orderID = ?";
    private static final String PRODUCT_EXIST = "SELECT quantity FROM addCart WHERE orderID = ? AND productID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE addCart SET quantity = ? WHERE orderID = ? AND productID = ?";

    @Override
    public void insert(int orderID, int productID, int quantity){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART)){
            preparedStatement.setInt(1,orderID);
            preparedStatement.setInt(2,productID);
            preparedStatement.setInt(3,quantity);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int orderID, int productID){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)){
            preparedStatement.setInt(1,orderID);
            preparedStatement.setInt(2,productID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProduct(int orderID){
        Connection connection = dbConnection.getConnection();
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT)){

            preparedStatement.setInt(1, orderID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("ProductID"));
                    product.setName(resultSet.getString("ProductName"));
                    product.setIce(resultSet.getString("ice"));
                    product.setSize(resultSet.getString("size"));
                    product.setSugar(resultSet.getString("sugar"));
                    Integer quantity = resultSet.getInt("quantity");
                    product.setPrice(resultSet.getInt("price") * quantity);
                    product.setQuantity(quantity);

                    productList.add(product);
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Integer ProductExist(int orderID, int productID){
        Connection connection = dbConnection.getConnection();
        Integer quantity = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_EXIST)){

            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    @Override
    public void updateQuantity(int orderID, int productID, int Quantity){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)){

            preparedStatement.setInt(1, Quantity);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, productID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
