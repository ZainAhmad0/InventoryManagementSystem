package Stock;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

public class StockDAOImpl implements StockDAO {
    private static String ADD_PRODUCT_STOCK = "INSERT INTO InventoryManagementSystem.Stock\n" +
            "(itemsInStock)\n" +
            "VALUES({0});";

    private  static final String DELETE_BY_PRODUCT_ID = "DELETE  FROM InventoryManagementSystem.Stock WHERE Product_Id = {0};";
    @Override
    public boolean addProductStock(int itemsInStock) throws SQLException {
        Connection conn = DB.connectDB();
        String insert = MessageFormat.format(ADD_PRODUCT_STOCK,itemsInStock);
        PreparedStatement statement = conn.prepareStatement(insert);
        int count = statement.executeUpdate();
        if(count==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProduct(int idOfProduct) throws SQLException {
        Connection conn = DB.connectDB();
        String insert = MessageFormat.format(DELETE_BY_PRODUCT_ID,idOfProduct);
        PreparedStatement statement = conn.prepareStatement(insert);
        int count = statement.executeUpdate();
        if(count==0){
            return false;
        }
        return true;
    }
}
