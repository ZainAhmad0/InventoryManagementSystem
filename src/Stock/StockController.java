package Stock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockController {
    private StockService stockService = new StockServiceImpl();
    private int itemsInStock;
    private Scanner obj = new Scanner(System.in);
    public boolean addProductStock() throws SQLException {
        System.out.print("Enter Stock : ");
        itemsInStock=obj.nextInt();
        return stockService.addProductStock(itemsInStock);
    }

    public void deleteProduct(int idOfProduct) throws SQLException {
        stockService.deleteProduct(idOfProduct);
    }

    public void getStock() throws SQLException {
        stockService.getStock();
    }

    public void updateStock(ArrayList<StockDTO> stockDTOS) throws SQLException{
        stockService.updateStock(stockDTOS);
    }

    public void displayStock() throws SQLException {
        ArrayList<StockDTO> stockDTOS = new ArrayList<StockDTO>();
        stockDTOS=stockService.getStock();
        System.out.println("Product ID                   Items In Stock");
        for (int i=0; i<stockDTOS.size(); i++){
            System.out.println(stockDTOS.get(i).getProductID()+"                    "+stockDTOS.get(i).getItemsInStock());
        }
    }
}
