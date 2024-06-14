package dao.user;


import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;

import bean.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private DBConnection dbConnection = new DBConnectionImpl();
    private  static  final String GET_LOGIN = "SELECT * FROM user WHERE account = ? AND password = ?";
    private  static  final String GET_ACCOUNT = "SELECT account FROM user WHERE account = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO user(account, password, phone,level) VALUES (?,?,?,?)";
    private static final String GET_ID = "SELECT UserID FROM user WHERE account = ?";
    private static final String GET_ALL_USER = "Select Account, Level, Phone from user;";
    private static final String UPDATE_USER_PWD_AND_TEL = "UPDATE user SET Password = ? , Phone = ? WHERE Account = ?";

    @Override
    public User getLogin(String account, String password){

        Connection connection = dbConnection.getConnection();
        User user = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN))
        {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            System.out.println("prepared:"+preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setAccount(resultSet.getString("account"));
                    user.setPassword(resultSet.getString("password"));
                    user.setLevel(resultSet.getInt("level"));
                    user.setPhone(resultSet.getString("Phone"));
                    user.setId(resultSet.getInt("userid"));
                }
                else{
                    return user;
                }
            }
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String getAccount(String inAccount){

        Connection connection = dbConnection.getConnection();
        String account = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT))
        {
            preparedStatement.setString(1, inAccount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    account = resultSet.getString("account");
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public void insert(String newAccount,String pwd,String phone,int level){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER))
        {
            preparedStatement.setString(1,newAccount);
            preparedStatement.setString(2,pwd);
            preparedStatement.setString(3,phone);
            preparedStatement.setInt(4,level);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Integer getCustomerID(String account){
        Connection connection = dbConnection.getConnection();
        int customerID = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ID))
        {
            preparedStatement.setString(1, account);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    customerID = resultSet.getInt("UserID");
                }
                else{
                    return customerID;
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return customerID;
    }

    @Override
    public List<User> getAllInfo() {
        List<User> userList = new ArrayList<>();
        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setAccount(resultSet.getString("Account"));
                    user.setLevel(resultSet.getInt("Level"));
                    user.setPhone(resultSet.getString("Phone"));
                    System.out.println("user:"+user);
                    userList.add(user);
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void updateUserInfo(User user) {
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PWD_AND_TEL))
        {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getAccount());
            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
