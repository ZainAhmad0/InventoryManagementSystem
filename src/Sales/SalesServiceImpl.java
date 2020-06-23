package Sales;

import Product.ProductController;
import Product.ProductDAOImpl;
import Stock.StockDAO;
import Stock.StockDAOImpl;
import Stock.StockDTO;
import exception.ObjectNotFound;

import java.sql.ResultSet;
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
        ArrayList<Integer> removeProducts = new ArrayList<Integer>();
        stockDTOS = stockDAO.getStock();
        for (int j = 0; j < salesDTO.getProductInfo().size(); j++) {
            for (int i = 0; i < stockDTOS.size(); i++) {
                if (salesDTO.getProductInfo().get(j).getProductID() == stockDTOS.get(i).getProductID()) {
                    if (stockDTOS.get(i).getItemsInStock() == 0) {
                        System.out.println("Product ID : " + salesDTO.getProductInfo().get(j).getProductID() + " Not in stock, And removed from card");
                        removeProducts.add(j);
                        break;
                    }
                    if ((stockDTOS.get(i).getItemsInStock() - salesDTO.getProductInfo().get(j).getQuantity()) >= 0) {
                        stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - salesDTO.getProductInfo().get(j).getQuantity()));
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Product ID : " + salesDTO.getProductInfo().get(j).getProductID());
                        System.out.println("Quantity Available In Stock : " + stockDTOS.get(i).getItemsInStock());
                        System.out.println("Quantity Entered : " + salesDTO.getProductInfo().get(j).getQuantity());
                        int quantity;
                        System.out.print("Please Re Enter Quantity (Should be Equal Or less than Stock Available) : ");
                        quantity = obj.nextInt();
                        SalesProductDTO salesProductDTO = new SalesProductDTO();
                        if ((stockDTOS.get(i).getItemsInStock() - quantity) >= 0) {
                            stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - quantity));
                            salesProductDTO.setProductID(salesDTO.getProductInfo().get(j).getProductID());
                            salesProductDTO.setQuantity(quantity);
                            salesDTO.getProductInfo().set(j, salesProductDTO);

                        } else {
                            quantity = stockDTOS.get(i).getItemsInStock();
                            System.out.print("By Default Now Quantity = " + (stockDTOS.get(i).getItemsInStock()));
                            salesProductDTO.setQuantity(quantity);
                            salesProductDTO.setProductID(salesDTO.getProductInfo().get(j).getProductID());
                            salesDTO.getProductInfo().set(j, salesProductDTO);
                            stockDTOS.get(i).setItemsInStock((stockDTOS.get(i).getItemsInStock() - quantity));
                        }
                        break;
                    }

                }
            }
        }
        int temp;
        for (int i = 0; i < removeProducts.size(); i++) {
            temp = removeProducts.get(i);
            salesDTO.getProductInfo().remove(temp);
        }
        stockDAO.updateStock(stockDTOS);
        return salesDTO;
    }

    @Override
    public ResultSet getPreviousSales(String userName) throws SQLException {
        return salesDAO.getPreviousSales(userName);
    }

    @Override
    public ResultSet calculateProfit() throws SQLException {
        return salesDAO.calculateProfit();
    }
}
