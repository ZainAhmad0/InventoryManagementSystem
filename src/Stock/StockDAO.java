package Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDAO {
    boolean addProductStock(int itemsInStock) throws SQLException;

    boolean deleteProduct(int idOfProduct) throws SQLException;

    public ArrayList<StockDTO> getStock() throws SQLException;

    public void updateStock(ArrayList<StockDTO> stockDTOS) throws SQLException;

}
