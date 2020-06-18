package Product;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductController obj= new ProductController();
        //obj.updateProductSalesPrice();
         // obj.updateProductName();
       // obj.addProduct();
      // obj.showTable("Products");
        obj.deleteProduct(5);
        obj.showProductsByCategory("Mobiles");
        /*
        try{
            obj.deleteProduct(1);
        } catch (ObjectNotFound e){
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        */ // delete product

        // obj.deleteProduct(1);
//        Scanner ob = new Scanner(System.in);
//        int choice =0;
//        while(choice==0){
//            obj.addProduct();
//        }

    }
}
