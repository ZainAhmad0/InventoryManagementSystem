package Stock;

import java.sql.SQLException;

public interface StockService {
    boolean addProductStock(int itemsInStock) throws SQLException;
    boolean deleteProduct(int idOfProduct) throws SQLException;
}
