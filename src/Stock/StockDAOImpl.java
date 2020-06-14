package Stock;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    private static String ADD_PRODUCT_STOCK = "INSERT INTO InventoryManagementSystem.Stock\n" +
            "(itemsInStock)\n" +
            "VALUES({0});";

    private static String GET_STOCK = "SELECT * FROM InventoryManagementSystem.Stock;";
    private static String UPDATE_STOCK = "UPDATE InventoryManagementSystem.Stock\n" +
            "SET itemsInStock={0}\n" +
            "WHERE Product_Id={1};\n";

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

    @Override
    public ArrayList<StockDTO> getStock() throws SQLException {
        ArrayList<StockDTO> stockDTOS = new ArrayList<StockDTO>();
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(GET_STOCK);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            StockDTO stockDTO = new StockDTO();
            stockDTO.setProductID(result.getInt("Product_Id"));
            stockDTO.setItemsInStock(result.getInt("itemsInStock"));
            stockDTOS.add(stockDTO);
        }
        return stockDTOS;
    }

    @Override
    public void updateStock(ArrayList<StockDTO> stockDTOS) throws SQLException {
        Connection conn = DB.connectDB();
        for (int i = 0; i < stockDTOS.size(); i++) {
            String update = MessageFormat.format(UPDATE_STOCK, stockDTOS.get(i).getItemsInStock(),stockDTOS.get(i).getProductID());
            PreparedStatement statement = conn.prepareStatement(update);
            statement.executeUpdate();
        }
    }
}
