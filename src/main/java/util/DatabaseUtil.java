package util;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static String dropAllTableScript = "DB/initScript/dropAllTable.sql";
    private static String createTableScript = "DB/initScript/createTable.sql";
    private static String insertDefaultDataScript = "DB/initScript/insertDefaultData.sql";
    private static String insertDefaultProductScript = "DB/initScript/insertDefaultProduct.sql";
    public static void executeSQLFile(String filePath) {
        DBConnection dbConnection = new DBConnectionImpl();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            StringBuilder sql = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sql.append(line);
            }

            for (String sqlStatement : sql.toString().split(";")) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.execute(sqlStatement);
                }
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public static void initDatabase(){
        executeSQLFile(dropAllTableScript);
        executeSQLFile(createTableScript);
        executeSQLFile(insertDefaultProductScript);
        executeSQLFile(insertDefaultDataScript);
    }
}
