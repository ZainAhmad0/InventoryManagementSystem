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
    private ArrayList<SalesProductDTO> temp = new ArrayList<SalesProductDTO>();
    SalesProductDTO salesProductDTO = new SalesProductDTO();


    @Override
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException {
        salesDAO.addProductRecord(salesDTO, username);
    }

    @Override
    public SalesDTO buyProducts(SalesDTO salesDTO, String category) throws SQLException {
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            try {
                productDAO.validateProductByIdAndCategory(salesDTO.getProductInfo().get(i).getProductID(), category);
            } catch (ObjectNotFound e) {
                System.out.println(e.getMessage());
                System.out.println("Product id : " + salesDTO.getProductInfo().get(i).getProductID() + " Discarded from Product list");
                salesProductDTO.setProductID(salesDTO.getProductInfo().get(i).getProductID());
                salesProductDTO.setQuantity(salesDTO.getProductInfo().get(i).getQuantity());
                temp.add(salesProductDTO);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            salesDTO.getProductInfo().remove(temp.get(i));
        }
        return salesDTO;
    }
}
