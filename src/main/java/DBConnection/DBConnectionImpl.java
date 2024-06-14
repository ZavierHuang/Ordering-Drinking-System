package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionImpl implements DBConnection{

    private static final String URL = "jdbc:mysql://localhost:3306/ooad_db?" +"encrypt=true;trustServerCertificate=true";
    private static final String user="root";
    private static final String password="root";

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return conn;
    }
}