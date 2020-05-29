package database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection connectDB() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "InventoryManagementSystem";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+"?useSSL=false",userName,password);

            // System.out.println("Connected to the database");

            //conn.close();
            //System.out.println("Disconnected from database");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;
    }
}
