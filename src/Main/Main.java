package Main;

import Barcode.BarcodeController;
import Product.ProductController;
import Sales.SalesController;
import Sales.SalesDTO;
import Stock.StockController;
import Stock.StockDTO;
import User.UserController;
import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner obj = new Scanner(System.in);
    private static String username, password;
    private static UserController userController = new UserController();
    private static ProductController productController = new ProductController();
    private static SalesController salesController = new SalesController();
    private static BarcodeController barcodeController = new BarcodeController();
    private static SalesDTO salesDTO = new SalesDTO();
    private static StockController stockController = new StockController();


    public static void main(String[] args) throws Exception {
        int choice;
        System.out.println("                                  Welcome to MFS Shopping mall                          ");
        System.out.println();
        System.out.println();
        do {
            System.out.println("1 : Sign In ");
            System.out.println("2 : Sign up");
            System.out.println("Press Any other digit to exit");
            choice = obj.nextInt();
            LoginDTO loginDTO = new LoginDTO();
            obj.nextLine();
            if (choice == 1) {
                char choice1;
                System.out.println("x: User");
                System.out.println("y: Admin");
                System.out.println("Press Any other character to exit");
                choice1 = obj.next().charAt(0);
                obj.nextLine();
                if (choice1 == 'x') {
                    loginDTO.setUserType(0);
                    loginDTO = signIn(loginDTO);
                    userInterface(loginDTO.getUsername());
                    continue;
                } else if (choice1 == 'y') {
                    loginDTO.setUserType(1);
                    loginDTO = signIn(loginDTO);
                    adminInterface();
                } else {
                    System.out.println("Invalid choice Entered...");
                    System.exit(0);
                }
            } else if (choice == 2) {
                if (userController.registerUser()) {
                    System.out.println();
                    System.out.println();
                    System.out.println("******************* User Registered Successfully *********************");
                    System.out.println();
                    System.out.println();
                    loginDTO = signIn(loginDTO);
                    userInterface(loginDTO.getUsername());
                    continue;
                }
                System.out.println();
                continue;
            } else {
                System.out.println("Thank you");
                System.exit(0);
            }

        } while (choice == 1 || choice == 2);

    }


    private static LoginDTO signIn(LoginDTO loginDTO) throws Exception {
        System.out.println("Please Enter Required Credentials to sign in");
        System.out.print("Enter Your Username : ");
        username = obj.nextLine();
        System.out.print("Enter Your password : ");
        password = obj.nextLine();
        loginDTO.setPassword(password);
        loginDTO.setUsername(username);
        while (!userController.isUserValid(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getUserType())) {
            System.out.println("Invalid credentials entered, please re enter to login...");
            System.out.print("Enter Your Username : ");
            username = obj.nextLine();
            System.out.print("Enter Your password : ");
            password = obj.nextLine();
            loginDTO.setPassword(password);
            loginDTO.setUsername(username);
        }
        System.out.println();
        System.out.println();
        System.out.println("                        Login Successful  ");
        System.out.println();
        return loginDTO;
    }

    private static void userInterface(String username) throws Exception {
        int choice;
        do {
            System.out.println("1: Buy Products");
            System.out.println("2: View Previous Sales");
            System.out.println("3: Logout");
            System.out.println("Press any other digit to Logout and Exit");
            choice = obj.nextInt();
            if (choice == 1) {
                char choiceCategory;
                String category = "";
                System.out.println();
                System.out.println();
                System.out.println("Select Category");
                System.out.println();
                System.out.println("a: Laptops");
                System.out.println("b: Mobiles");
                System.out.println("c: Men Clothes");
                System.out.println("Press any other character to logout, and exit");
                choiceCategory = obj.next().charAt(0);
                obj.nextLine();
                if (choiceCategory == 'a') {
                    productController.showProductsByCategory("Laptops");
                    category = "Laptops";
                } else if (choiceCategory == 'b') {
                    productController.showProductsByCategory("Mobiles");
                    category = "Mobiles";
                } else if (choiceCategory == 'c') {
                    productController.showProductsByCategory("Men Clothes");
                    category = "Men Clothes";
                } else {
                    return;
                }
                salesDTO = salesController.buyProducts(username);
                salesDTO = salesController.validateProducts(salesDTO, category);
                barcodeController.createBarcode(salesDTO);
                salesDTO = barcodeController.validateBarcodes(salesDTO);
                salesDTO = salesController.validateProductInStock(salesDTO);
                if (salesDTO.getProductInfo().size() > 0) {
                    salesController.addProductRecord(salesDTO, username);
                    salesController.generateReceipt(salesDTO, username);
                } else {
                    System.out.println("Thank you");
                    System.out.println();
                    return;
                }

            } else if (choice == 2) {
                salesController.showPreviousSales(username);
            } else if (choice == 3) {
                return;
            } else {
                System.exit(0);
            }
        } while (choice == 1 || choice == 2);
    }

    private static void adminInterface() throws Exception {
        char choice;
        do{
            System.out.println();
            System.out.println();
            System.out.println("a: Add new Product");
            System.out.println("b: Update Product");
            System.out.println("c: Delete User");
            System.out.println("d: Update Stock");
            System.out.println("e: View Total Profit");
            System.out.println("f: Delete Product");
            System.out.println("g: Logout");
            System.out.println("Press any other character to logout, and exit");
            choice = obj.next().charAt(0);
            obj.nextLine();
            switch (choice) {
                case 'a': {
                    productController.addProduct();
                    System.out.println("Product Added Successfully");
                    break;
                }
                case 'b': {
                    int choice1;
                    System.out.println("1: Update Product Name");
                    System.out.println("2: Update product Sales Price");
                    System.out.println("3: Update Product Category");
                    System.out.println("4: Go back to main menu");
                    System.out.println("Press any other digit to Logout, and exit");
                    choice1 = obj.nextInt();
                    obj.nextLine();
                    if(choice1==1){
                        productController.updateProductName();
                        System.out.println("Product Name Updated");
                    }
                    else if(choice1==2){
                        productController.updateProductSalesPrice();
                        System.out.println("Product Sales Price Updated");
                    }
                    else if(choice1==3){
                        productController.updateProductCategory();
                        System.out.println("Product Category Updated");
                    }
                    else if(choice1==4){
                        continue;
                    }
                    else{
                        return;
                    }
                    break;
                }
                case 'c': {
                    userController.getRegisteredUser();
                    System.out.println();
                    System.out.print("Enter Username : ");
                    String username1;
                    username1=obj.nextLine();
                    if(userController.deleteUser(username1)){
                        System.out.println("Username : "+username1+" Successfully deleted.");
                    }
                    else {
                        System.out.println("Invalid username entered.");
                        continue;
                    }
                    break;
                }
                case 'd': {
                    System.out.println();
                    System.out.println("Current Stock");
                    System.out.println();
                    stockController.displayStock();
                    System.out.println();
                    int choice2=0;
                    int productID;
                    int quantity;
                    do{
                        ArrayList<StockDTO> stockDTOS = new ArrayList<StockDTO>();
                        StockDTO stockDTO = new StockDTO();
                        System.out.print("Enter Product Id : ");
                        productID=obj.nextInt();
                        System.out.print("Enter Quantity : ");
                        quantity=obj.nextInt();
                        stockDTO.setItemsInStock(quantity);
                        stockDTO.setProductID(productID);
                        stockDTOS.add(stockDTO);
                        stockController.updateStock(stockDTOS);
                        System.out.println();
                        System.out.println("Stock Updated");
                        System.out.println();
                        System.out.println("1: Go back to main menu");
                        System.out.println("Press any other digit to update more stock");
                        choice2=obj.nextInt();
                    }while (choice2!=1);
                    break;
                }
                case 'e': {
                    break;
                }
                case 'f': {
                    System.out.println("Current Products available are ...");
                    System.out.println();
                    productController.showTable("Products");
                    System.out.println();
                    System.out.print("Please Enter Product ID : ");
                    int productID = obj.nextInt();
                    try{
                        if(productController.deleteProduct(productID)){
                            System.out.println("Product Id : "+ productID + " deleted successfully");
                        }
                    } catch (ObjectNotFound e){
                        System.out.println(e);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
                case 'g': {
                    return;
                }
                default: {
                    System.out.println();
                    System.out.println("Thank you");
                    System.exit(0);
                }
            }

        }while(choice=='a'||choice=='b'||choice=='c'||choice=='d'||choice=='e'||choice=='f'||choice=='g');


    }

}
