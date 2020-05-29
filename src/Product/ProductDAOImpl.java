package Product;
import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class ProductDAOImpl implements ProductDAO{
    private static final String CREATE = "INSERT INTO InventoryManagementSystem.Products\n" +
            "(Product_Name, Sales_Price, Purchase_Price, Category)\n" +
            "VALUES(''{0}'', ''{1}'', ''{2}'', ''{3}'');\n";
    private  static final String FIND_BY_NAME = "SELECT * FROM InventoryManagementSystem.Products WHERE Product_Name = ''{0}''";
    private  static final String FIND_NUMBER_OF_ROWS = "SELECT COUNT(*)  from InventoryManagementSystem.{0};";
    private  static final String FIND_PRODUCTS = "SELECT * from InventoryManagementSystem.{0};";
    private  static final String FIND_PRODUCTS_BY_CATEGORY = "SELECT * from InventoryManagementSystem.Products WHERE Category = ''{0}'';";
    private static final String update ="UPDATE InventoryManagementSystem.Products\n" +
            "SET Product_Name=''{0}'', Sales_Price= {1}, Purchase_Price= {2}, Category=''{3}''\n" +
            "WHERE Product_Id= {4} ;\n";
    @Override
    public ProductDTO addProduct(ProductDTO product) throws SQLException {
        Connection conn = DB.connectDB();
        String insertQuery = MessageFormat.format(CREATE, product.getProductName(),
        product.getSalesPrice(),product.getPurchasePrice(),product.getCategory());
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        int count = statement.executeUpdate();
        return product;
    }

    @Override
    public ProductDTO findProductByName(String name) throws SQLException {
        String findQuery = MessageFormat.format(FIND_BY_NAME,name);
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(findQuery);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            ProductDTO product1 = new ProductDTO();
            product1.setSalesPrice(result.getDouble("Sales_Price"));
            product1.setCategory(result.getString("Category"));
            product1.setPurchasePrice(result.getDouble("Purchase_Price"));
            product1.setProductName(result.getString("Product_Name"));
            product1.setProductID(result.getInt("Product_Id"));
            return product1;
        }else{
            return null;
        }
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) throws SQLException {
        String findQuery = MessageFormat.format(update,product.getProductName(),product.getSalesPrice(),product.getPurchasePrice(),product.getCategory(),product.getProductID());
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(findQuery);
        statement.executeUpdate();
        return product;
    }
    public int getNumOfRows(String tableName) throws SQLException {
        String findQuery = MessageFormat.format(FIND_NUMBER_OF_ROWS,tableName);
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(findQuery);
        ResultSet result = statement.executeQuery();
        result.next();
        return (result.getInt("COUNT(*)"));
    }

    @Override
    public ProductDTO[] showTable(String tableName) throws SQLException {
        Connection conn = DB.connectDB();
        String findQuery = MessageFormat.format(FIND_PRODUCTS,tableName);
        PreparedStatement statement = conn.prepareStatement(findQuery);
        ResultSet result = statement.executeQuery();
        int count=0;
        ProductDTO[] products = new ProductDTO[getNumOfRows("Products")];
        while(result.next()){
            ProductDTO temp = new ProductDTO();
            temp.setSalesPrice(result.getDouble("Sales_Price"));
            temp.setCategory(result.getString("Category"));
            temp.setPurchasePrice(result.getDouble("Purchase_Price"));
            temp.setProductName(result.getString("Product_Name"));
            temp.setProductID(result.getInt("Product_Id"));
            products[count++]=temp;
        }
        return products;

    }




    @Override
    public ProductDTO[] findProductsByCategory(String category) throws SQLException {
        String findQuery = MessageFormat.format(FIND_PRODUCTS_BY_CATEGORY,category);
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(findQuery);
        ResultSet result = statement.executeQuery();
        int rows=0;
        while(result.next()){
            rows++;
        }
        result.beforeFirst();
        int count=0;
        ProductDTO[] products = new ProductDTO[rows];
        while(result.next()){
            ProductDTO temp = new ProductDTO();
            temp.setSalesPrice(result.getDouble("Sales_Price"));
            temp.setCategory(result.getString("Category"));
            temp.setPurchasePrice(result.getDouble("Purchase_Price"));
            temp.setProductName(result.getString("Product_Name"));
            temp.setProductID(result.getInt("Product_Id"));
            products[count++]=temp;
        }
        return products;
    }

}
