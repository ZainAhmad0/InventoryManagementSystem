package Product;

import exception.ObjectNotFound;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductController obj= new ProductController();
        //obj.updateProductSalesPrice();
         // obj.updateProductName();
        obj.addProduct();
      // obj.showTable("Products");
       // obj.showProductsByCategory("Clothes");
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

    }
}
