package Sales;

import Product.ProductController;
import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesServiceImpl implements SalesService {
    private ProductController productController = new ProductController();
    private SalesDAO salesDAO = new SalesDAOImpl();
    private SalesController salesController = new SalesController();

    @Override
    public void buyProducts(ArrayList<Integer> products, ArrayList<Integer> quantity, String category,String username) throws SQLException {
        for (int i = 0; i < products.size(); i++) {
            try {
                productController.validateProductByIdAndCategory(products.get(i), category);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
                System.out.println("Product id : " + products.get(i) + " Discarded from products list..");
                products.remove(i);
                quantity.remove(i);
            }
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
            System.out.println(quantity.get(i));
        }

       // salesDAO.buyProducts(products, quantity,username);
       // salesController.generateReceipt(products, quantity,username);
    }
}
