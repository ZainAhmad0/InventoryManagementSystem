package Sales;

import Product.ProductController;
import Product.ProductDAOImpl;
import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class SalesServiceImpl implements SalesService {
    private SalesDAO salesDAO = new SalesDAOImpl();
    private ProductDAOImpl productDAO = new ProductDAOImpl();
    private Integer salesID;
    private ArrayList<Integer> temp = new ArrayList<Integer>();


    @Override
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesDAO.addProductRecord(salesDTO.getProducts(),salesDTO.getProductQuantity(),username);
    }

    @Override
    public SalesDTO buyProducts(ArrayList<Integer> products, ArrayList<Integer> quantity, String category) throws SQLException {
        for (int i = 0; i < products.size(); i++) {
            try {
                productDAO.validateProductByIdAndCategory(products.get(i), category);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
                System.out.println("Product id : " + products.get(i) + " Discarded from Product list");
                temp.add(products.get(i));

            }
        }
        for(int i=0; i<temp.size(); i++){
            products.remove(temp.get(i));
            quantity.remove(temp.get(i));
        }
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.setProducts(products);
        salesDTO.setProductQuantity(quantity);
        return salesDTO;
    }
}
