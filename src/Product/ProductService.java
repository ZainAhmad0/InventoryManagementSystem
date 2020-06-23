package Product;

import exception.ObjectNotFound;

import java.sql.SQLException;

public interface ProductService {
    ProductDTO addProduct(ProductDTO product) throws SQLException;

    ProductDTO updateProduct(String existingName, ProductDTO product) throws SQLException;

    int getNumOfProducts(String tableName) throws SQLException;

    ProductDTO[] showTable(String tableName) throws SQLException;

    ProductDTO[] findProductsByCategory(String category) throws SQLException;

    boolean deleteProduct(int idOfProduct) throws SQLException;

    boolean validateProductByIdAndCategory(int productID, String category) throws SQLException, ObjectNotFound;
}
