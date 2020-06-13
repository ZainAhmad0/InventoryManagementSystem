package Sales;

import java.util.*;

import Barcode.BarcodeController;
import Product.ProductController;

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


    SalesDTO buyProducts(String username) throws Exception {
        System.out.println("1: Mobiles");
        System.out.println("2: Laptops");
        System.out.println("3: Men Clothes");
        // choice would be entered by the user here.
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

    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesService.addProductRecord(salesDTO, username);
    }

    public void generateReceipt(ArrayList<Integer> products, ArrayList<Integer> productQuantity, String username) {

    }
}
