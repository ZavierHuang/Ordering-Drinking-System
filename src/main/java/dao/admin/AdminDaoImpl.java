package dao.admin;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDAO {


    private DBConnection dbConnection = new DBConnectionImpl();

    private  static  final String GET_ALL_EMP = "SELECT * FROM user WHERE level >= 10 AND level < 99";
    private  static  final String GET_SINGLE_EMP = "SELECT * FROM user WHERE userid = ? and level >= 10 AND level < 99";
    private static final String INSERT_EMP = "INSERT INTO user(account, password, phone,level) VALUES (?,?,?,?)";
    private static final String UPDATE_EMP = "UPDATE user SET account = ?, password = ?, phone = ?, level = ? WHERE userid = ?";
    private static final String DELETE_ALL_EMP = "DELETE FROM user where level >= 10 AND level < 99";
    private static final String DELETE_EMP_BY_ACC = "DELETE FROM user where level >= 10 AND level < 99 AND account=?";
    private static final String UPDATE_EMP_BY_ACC = "UPDATE user SET password = ?, phone = ?, level = ? WHERE account = ?";

    private static final String DELETE_BUILD_BY_ACC = "DELETE FROM build WHERE UserID IN (SELECT UserID FROM user WHERE level >= 10 AND level < 99 AND account=?)";

    //註冊員工帳號
    public int insertEmployee(User user){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMP))
        {
            preparedStatement.setString(1,user.getAccount());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setInt(4,user.getLevel());

            preparedStatement.executeUpdate();
            connection.close();
            return 1;//Success
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;//Failed
        }
    }


    //修改員工資料 (帳號 密碼 電話等
    public int updateEmployee(User user){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP))
        {
            preparedStatement.setString(1,user.getAccount());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setInt(4,user.getLevel());
            preparedStatement.setInt(5,user.getId());

            preparedStatement.executeUpdate();
            connection.close();
            return 1;//Success
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;//Failed
        }
    }




    //取消員工資格 level = -1
    public int deleteAllEmployee(){
        Connection connection = dbConnection.getConnection();


        try {
            connection.setAutoCommit(false);  // 開始事務

            // 假設先刪除依賴於 user 的 build 表中的記錄
            try (PreparedStatement deleteBuild = connection.prepareStatement("DELETE FROM build WHERE UserID IN (SELECT UserID FROM user WHERE level >= 10 AND level < 99)")) {
                deleteBuild.executeUpdate();
            }

            // 然後刪除 user 表中的記錄
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_EMP)) {
                preparedStatement.executeUpdate();
            }

            connection.commit();  // 提交事務
            return 1;  // Success
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();  // 事務回滾
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return 0;  // Failed
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public int deleteEmployeebyId(User user){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_EMP))
        {
            preparedStatement.setString(1,user.getAccount());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setInt(4,user.getLevel());
            preparedStatement.setInt(5,user.getId());

            preparedStatement.executeUpdate();
            connection.close();
            return 1;//Success
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;//Failed
        }
    }


    public int deleteEmployeebyAcc(String account){
        Connection connection = dbConnection.getConnection();


        try {
            connection.setAutoCommit(false);  // 開始事務

            // 假設先刪除依賴於 user 的 build 表中的記錄
            try (PreparedStatement deleteBuild = connection.prepareStatement(DELETE_BUILD_BY_ACC)) {
                deleteBuild.setString(1,account);
                deleteBuild.executeUpdate();
            }

            // 然後刪除 user 表中的記錄
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMP_BY_ACC)) {
                preparedStatement.setString(1,account);
                preparedStatement.executeUpdate();
            }

            connection.commit();  // 提交事務
            return 1;  // Success
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();  // 事務回滾
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return 0;  // Failed
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public int updateEmployeebyAcc(User user) {
        Connection connection = dbConnection.getConnection();


        try {
            connection.setAutoCommit(false);  // 開始事務



            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP_BY_ACC)) {
                preparedStatement.setString(1,user.getPassword());
                preparedStatement.setString(2,user.getPhone());
                preparedStatement.setInt(3,user.getLevel());
                preparedStatement.setString(4,user.getAccount());
                preparedStatement.executeUpdate();
            }

            connection.commit();  // 提交事務
            return 1;  // Success
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();  // 事務回滾
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return 0;  // Failed
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }






//        return 0;
    }


    //查詢員工 by id
    public User getUserbyId(int userId){
        Connection connection = dbConnection.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SINGLE_EMP)){
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()){
//                    empList.add(new User(resultSet.getString("account"),resultSet.getString("password"),resultSet.getInt("level"),resultSet.getString("phone")));
//                }
                if (resultSet.next()) {
                    user = new User(resultSet.getString("account"),resultSet.getString("password"),resultSet.getInt("level"),resultSet.getString("phone"));

                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    //查詢員工 all
    public List<User> getAllUsers(){
        Connection connection = dbConnection.getConnection();
        List<User> empList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMP)){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    empList.add(new User(resultSet.getString("account"),resultSet.getString("password"),resultSet.getInt("level"),resultSet.getString("phone")));
                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return empList;
    }


}
