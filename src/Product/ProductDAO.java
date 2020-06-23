package Product;

import exception.ObjectNotFound;

import java.sql.SQLException;

public interface ProductDAO {
    ProductDTO addProduct(ProductDTO product) throws SQLException;

    ProductDTO findProductByName(String name) throws SQLException;

    ProductDTO updateProduct(ProductDTO product) throws SQLException;

    int getNumOfRows(String tableName) throws SQLException;

    ProductDTO[] showTable(String tableName) throws SQLException;

    ProductDTO[] findProductsByCategory(String category) throws SQLException;

    boolean deleteProduct(int idOfProduct) throws SQLException;

    boolean validateProductByIdAndCategory(int productID, String category) throws SQLException, ObjectNotFound;
}
