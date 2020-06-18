package Product;
import Stock.StockController;
import exception.ObjectNotFound;
import java.sql.SQLException;
import java.text.MessageFormat;

public class ProductServiceImp implements ProductService {
    private final ProductDAO productDAO= new ProductDAOImpl();

    @Override
    public ProductDTO addProduct(ProductDTO product) throws SQLException {
        productDAO.addProduct(product);
        return null;
    }

    @Override
    public ProductDTO updateProduct(String existingName, ProductDTO product) throws SQLException {
        ProductDTO result = productDAO.findProductByName(existingName);
        if(result==null){
            throw new ObjectNotFound(MessageFormat.format("''{0}'' This product does not exists",existingName));
        }else{
            if(product.getProductName()!=null){
                result.setProductName(product.getProductName());
            }
            if(product.getCategory()!=null){
                result.setCategory(product.getCategory());
            }
            if(product.getSalesPrice()!=null){
                result.setSalesPrice(product.getSalesPrice());
            }
            if(product.getPurchasePrice()!=null){
                result.setPurchasePrice(product.getPurchasePrice());
            }
        }
        productDAO.updateProduct(result);
        return result;
    }

    public int getNumOfProducts(String tableName) throws SQLException {
        return productDAO.getNumOfRows(tableName);
    }

    @Override
    public ProductDTO[] showTable(String tableName) throws SQLException {
        return productDAO.showTable(tableName);
    }

    @Override
    public ProductDTO[] findProductsByCategory(String category) throws SQLException {
        return productDAO.findProductsByCategory(category);
    }

    @Override
    public boolean deleteProduct(int idOfProduct) throws SQLException {
        return productDAO.deleteProduct(idOfProduct);
    }

    @Override
    public boolean validateProductByIdAndCategory(int productID, String category) throws SQLException,ObjectNotFound{
        return productDAO.validateProductByIdAndCategory(productID,category);
    }
}
