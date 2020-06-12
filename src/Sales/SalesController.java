package Sales;

import java.util.*;

import Barcode.BarcodeController;
import Product.ProductController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesController {
    private Scanner obj = new Scanner(System.in);
    private ProductController productController = new ProductController();
    private int productId, quantity;
    private ArrayList<Integer> products = new ArrayList<Integer>();
    private ArrayList<Integer> productQuantity = new ArrayList<Integer>();
    private SalesDTO salesDTO = new SalesDTO();
    private boolean checker = true;
    private  SalesService salesService = new SalesServiceImpl();
    private int choice;


    SalesDTO buyProducts(String username) throws Exception {
        System.out.println("1: Mobiles");
        System.out.println("2: Laptops");
        System.out.println("3: Men Clothes");
        // choice would be entered by the user here.
        while (checker) {
            productController.showProductsByCategory("Laptops");
            System.out.print("Enter product id : ");
            productId = obj.nextInt();
            System.out.print("Enter Quantity : ");
            quantity = obj.nextInt();
            boolean checker1 = true;
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i) == productId) {
                    productQuantity.set(i, productQuantity.get(i) + quantity);
                    checker1 = false;
                    break;
                }
            }
            if (checker1) {
                products.add(productId);
                productQuantity.add(quantity);
            }
            System.out.println("Product Added To Cart Successfully ... ");
            System.out.println("1: Checkout");
            System.out.println("2: View Current Cart");
            System.out.println("3: Buy More...");
            choice = obj.nextInt();
            switch (choice) {
                case 1: {
                    checker = false;
                    System.out.println("Product Barcodes Generated Successfully");
                    break;
                }
                case 2: {
                    System.out.println("********** Current Cart **************");
                    System.out.println("Product ID                  Quantity");
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println(products.get(i) + "                        " + productQuantity.get(i));
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
        salesDTO.setProductQuantity(productQuantity);
        salesDTO.setProducts(products);
        return salesDTO;
    }
    public SalesDTO buyProductsAfterBarcodeValidation(ArrayList<Integer> products, ArrayList<Integer> productQuantity, String category) throws SQLException {
        return salesService.buyProducts(products,productQuantity,category);
    }

    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesService.addProductRecord(salesDTO,username);

    }

    public void generateReceipt(ArrayList<Integer> products, ArrayList<Integer> productQuantity, String username) {

    }
}
