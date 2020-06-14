package Sales;

import Product.ProductController;
import Product.ProductDAOImpl;
import Stock.StockDAO;
import Stock.StockDAOImpl;
import Stock.StockDTO;
import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SalesServiceImpl implements SalesService {
    private SalesDAO salesDAO = new SalesDAOImpl();
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private ArrayList<SalesProductDTO> tempProducts = new ArrayList<SalesProductDTO>();
    private StockDAO stockDAO = new StockDAOImpl();
    private Scanner obj = new Scanner(System.in);


    @Override
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesDAO.addProductRecord(salesDTO, username);
    }

    @Override
    public SalesDTO buyProducts(SalesDTO salesDTO, String category) throws SQLException {
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            try {
                productDAO.validateProductByIdAndCategory(salesDTO.getProductInfo().get(i).getProductID(), category);
                SalesProductDTO salesProductDTO = new SalesProductDTO();
                salesProductDTO.setProductID(salesDTO.getProductInfo().get(i).getProductID());
                salesProductDTO.setQuantity(salesDTO.getProductInfo().get(i).getQuantity());
                tempProducts.add(salesProductDTO);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
                System.out.println("Product id : " + salesDTO.getProductInfo().get(i).getProductID() + " Discarded from Product list");
            }
        }
        salesDTO.setProductInfo(tempProducts);
        return salesDTO;
    }

    @Override
    public SalesDTO validateProductInStock(SalesDTO salesDTO) throws SQLException {
        ArrayList<StockDTO> stockDTOS = new ArrayList<StockDTO>();
        stockDTOS = stockDAO.getStock();
        for (int i = 0; i < stockDTOS.size(); i++) {
            if (salesDTO.getProductInfo().get(i).getProductID() == stockDTOS.get(i).getProductID()) {
                if ((stockDTOS.get(i).getItemsInStock() - salesDTO.getProductInfo().get(i).getQuantity()) >= 0) {
                    stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - salesDTO.getProductInfo().get(i).getQuantity()));
                } else {
                    System.out.println();
                    System.out.println("Product ID : " + salesDTO.getProductInfo().get(i).getProductID());
                    System.out.println("Quantity Available In Stock : " + stockDTOS.get(i).getItemsInStock());
                    System.out.println("Quantity Entered : " + salesDTO.getProductInfo().get(i).getQuantity());
                    int quantity;
                    System.out.println("Please Re Enter Quantity (Should be Equal Or less than Stock Available) : ");
                    quantity = obj.nextInt();
                    if ((stockDTOS.get(i).getItemsInStock() - quantity) >= 0) {
                        stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - quantity));
                    } else {
                        quantity = stockDTOS.get(i).getItemsInStock();
                        System.out.println("By Default Now Quantity = " + (stockDTOS.get(i).getItemsInStock()));
                        SalesProductDTO salesProductDTO = new SalesProductDTO();
                        salesProductDTO.setQuantity(quantity);
                        salesProductDTO.setProductID(salesDTO.getProductInfo().get(i).getProductID());
                        salesDTO.getProductInfo().set(i, salesProductDTO);
                        stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - quantity));
                    }
                }

            }
        }
        stockDAO.updateStock(stockDTOS);
        return salesDTO;
    }
}
