package Sales;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SalesDAOImpl implements SalesDAO {
    private static final String INSERT_PRODUCTS = "INSERT INTO InventoryManagementSystem.Sales\n" +
            "(Sales_ID, Customer_Username, Product_ID, Quantity, Purchase_Date)\n" +
            "VALUES({0},''{1}'',{2},{3},''{4}'');";
    private Random random = new Random();
    private final int upperBound = 500000;
    private Integer salesID;

    @Override
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        Connection conn = DB.connectDB();
        Calendar calObj = Calendar.getInstance();
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            salesID = random.nextInt(upperBound);
            String insertQuery = MessageFormat.format(INSERT_PRODUCTS, salesID.toString(), username, salesDTO.getProductInfo().get(i).getProductID(), salesDTO.getProductInfo().get(i).getQuantity(), calObj.getTime().toString());
            PreparedStatement statement = conn.prepareStatement(insertQuery);
            statement.executeUpdate();
        }
    }
}
