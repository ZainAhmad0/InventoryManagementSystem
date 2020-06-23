package Sales;

import java.sql.ResultSet;
import java.util.*;

import Barcode.BarcodeController;
import Product.ProductController;
import Product.ProductDTO;
import Product.ProductServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesController {
    private Scanner obj = new Scanner(System.in);
    private int productId, quantity;
    private SalesDTO salesDTO = new SalesDTO();
    private boolean checker = true;
    private ArrayList<SalesProductDTO> productInfo = new ArrayList<SalesProductDTO>();
    private SalesService salesService = new SalesServiceImpl();
    private int choice;
    private Calendar calObj = Calendar.getInstance();
    private ProductServiceImp productServiceImp = new ProductServiceImp();
    private Double totalBill;


    public SalesDTO buyProducts(String username) throws Exception {
        while (checker) {
            System.out.print("Enter product id : ");
            productId = obj.nextInt();
            System.out.print("Enter Quantity : ");
            quantity = obj.nextInt();
            boolean checker1 = true;
            for (int i = 0; i < productInfo.size(); i++) {
                if (productInfo.get(i).getProductID() == productId) {
                    SalesProductDTO salesProductDTO = new SalesProductDTO();
                    salesProductDTO.setProductID(productInfo.get(i).getProductID());
                    salesProductDTO.setQuantity(productInfo.get(i).getQuantity() + quantity);
                    productInfo.set(i, salesProductDTO);
                    checker1 = false;
                    break;
                }
            }
            if (checker1) {
                SalesProductDTO salesProductDTO = new SalesProductDTO();
                salesProductDTO.setQuantity(quantity);
                salesProductDTO.setProductID(productId);
                productInfo.add(salesProductDTO);
            }
            System.out.println("Product Added To Cart Successfully ... ");
            System.out.println("1: Checkout");
            System.out.println("2: View Current Cart");
            System.out.println("3: Buy More...");
            choice = obj.nextInt();
            switch (choice) {
                case 1: {
                    checker = false;
                    break;
                }
                case 2: {
                    System.out.println("**********  Current Cart  **************");
                    System.out.println("Product ID                  Quantity");
                    for (int i = 0; i < productInfo.size(); i++) {
                        System.out.println(productInfo.get(i).getProductID() + "                              " + productInfo.get(i).getQuantity());
                    }
                    break;
                }
                case 3: {
                    continue;
                }
                default: {
                    System.out.println("Invalid Choice Entered...");
                    System.out.println("Checking out Current Products in cart by default...");
                    break;
                }
            }
        }
        salesDTO.setProductInfo(productInfo);
        return salesDTO;
    }

    public SalesDTO validateProducts(SalesDTO salesDTO, String category) throws SQLException {
        return salesService.buyProducts(salesDTO, category);
    }

    public SalesDTO validateProductInStock(SalesDTO salesDTO) throws SQLException {
        return salesService.validateProductInStock(salesDTO);
    }

    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesService.addProductRecord(salesDTO, username);
    }

    public void generateReceipt(SalesDTO salesDTO, String username) throws SQLException {
        totalBill = 0.0;
        System.out.println();
        System.out.println("                                    MFS Shopping Mall");
        System.out.println();
        System.out.println("Current Date And Time : " + calObj.getTime());
        ProductDTO[] productDTOS = productServiceImp.showTable("Products");
        System.out.println("Product Id               Quantity x Price Per Piece");
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            for (int j = 0; j < productDTOS.length; j++) {
                if (salesDTO.getProductInfo().get(i).getProductID() == productDTOS[j].getProductID()) {
                    System.out.println(salesDTO.getProductInfo().get(i).getProductID() + "                        " + salesDTO.getProductInfo().get(i).getQuantity() + " x " + productDTOS[j].getSalesPrice() + " = " + (salesDTO.getProductInfo().get(i).getQuantity() * productDTOS[j].getSalesPrice()));
                    totalBill = totalBill + (salesDTO.getProductInfo().get(i).getQuantity() * productDTOS[j].getSalesPrice());
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Your Total Bill Is : " + totalBill);
        System.out.println(username + ", Thank you for shopping");
    }

    public boolean showPreviousSales(String userName) throws SQLException {
        ResultSet resultSet = salesService.getPreviousSales(userName);
        int i = 1;
        if (resultSet.next()) {
            System.out.println(" Product ID                 Quantity                     Purchase Date and time");
            do {
                System.out.println((i++) + ". " + resultSet.getInt("Product_ID") + "                         " + resultSet.getInt("Quantity") + "                           " + resultSet.getString("Purchase_Date"));

            } while (resultSet.next());
            return true;
        } else {
            return false;
        }

    }

    public ResultSet calculateProfit() throws SQLException {
        return salesService.calculateProfit();
    }
}
