package Stock;

import java.sql.SQLException;

public class StockServiceImpl implements StockService {
    StockDAO stockDAO = new StockDAOImpl();

    @Override
    public boolean addProductStock(int itemsInStock) throws SQLException {
        return stockDAO.addProductStock(itemsInStock);
    }

    @Override
    public boolean deleteProduct(int idOfProduct) throws SQLException {
       return stockDAO.deleteProduct(idOfProduct);
    }
}
