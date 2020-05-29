package Stock;
import java.sql.SQLException;
import java.util.Scanner;

public class StockController {
    StockService stockService = new StockServiceImpl();
    private int itemsInStock;
    Scanner obj = new Scanner(System.in);
    public boolean addProductStock() throws SQLException {
        System.out.print("Enter Stock : ");
        itemsInStock=obj.nextInt();
        return stockService.addProductStock(itemsInStock);
    }

    public void deleteProduct(int idOfProduct) throws SQLException {
        stockService.deleteProduct(idOfProduct);
    }
}
