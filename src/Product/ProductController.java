package Product;

import Stock.StockController;
import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    private int productID;
    private String productName, category;
    private Double salesPrice, purchasePrice;
    private final ProductService productService = new ProductServiceImp();
    private StockController stockController = new StockController();
    private Scanner obj = new Scanner(System.in);
    private Scanner obj1 = new Scanner(System.in);

    public void addProduct() {
        try {
            System.out.print("Enter Product Name : ");
            productName = obj.nextLine();
            System.out.print("Enter Product Sales Price : ");
            salesPrice = obj.nextDouble();
            System.out.print("Enter Product Purchase Price : ");
            purchasePrice = obj.nextDouble();
            obj.nextLine();
            System.out.print("Enter Product Category : ");
            category = obj.nextLine();
            ProductDTO product = new ProductDTO();
            product.setCategory(category);
            product.setProductName(productName);
            product.setPurchasePrice(purchasePrice);
            product.setSalesPrice(salesPrice);
            stockController.addProductStock();
            productService.addProduct(product);
        } catch (ObjectNotFound e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateProductName() throws SQLException {
        System.out.print("Enter Existing Product Name : ");
        productName = obj.nextLine();
        String newProductName = new String();
        System.out.print("Enter New Product Name : ");
        newProductName = obj.nextLine();
        ProductDTO product = new ProductDTO();
        product.setProductName(newProductName);
        productService.updateProduct(productName, product);
    }

    public void updateProductCategory() throws SQLException {
        System.out.print("Enter Product Name : ");
        productName = obj1.nextLine();
        System.out.print("Enter New Product Category : ");
        category = obj1.nextLine();
        ProductDTO product = new ProductDTO();
        product.setCategory(category);
        productService.updateProduct(productName, product);
    }

    public void updateProductSalesPrice() throws SQLException {
        System.out.print("Enter Product Name : ");
        productName = obj1.nextLine();
        System.out.print("Enter New Sales Price of the Product : ");
        salesPrice = obj1.nextDouble();
        ProductDTO product = new ProductDTO();
        product.setSalesPrice(salesPrice);
        productService.updateProduct(productName, product);
    }

    public void showTable(String tableName) throws SQLException {
        ProductDTO[] products = new ProductDTO[getNumberOfRows(tableName)];
        products = productService.showTable(tableName);
        System.out.println("ID               Price               Category");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].getProductID() + "                  " + products[i].getSalesPrice() + "               " + products[i].getCategory());
        }
    }

    public void showProductsByCategory(String category) throws SQLException {
        ProductDTO[] products = productService.findProductsByCategory(category);
        for (int i = 0; i < products.length; i++) {
            System.out.println("******* Product " + (i + 1) + " *******");
            System.out.println("Product ID : " + products[i].getProductID());
            System.out.println("Product Name : " + products[i].getProductName());
            System.out.println("Price : " + products[i].getSalesPrice() + " /- Only.");
            System.out.println();
        }
    }

    public int getNumberOfRows(String tableName) throws SQLException {
        return productService.getNumOfProducts(tableName);
    }

    public boolean deleteProduct(int idOfProduct) throws SQLException {
        if (productService.deleteProduct(idOfProduct)) {
            stockController.deleteProduct(idOfProduct);
            return true;
        }
        return false;
    }

    public boolean validateProductByIdAndCategory(int productID, String category) throws SQLException, ObjectNotFound {
        return productService.validateProductByIdAndCategory(productID, category);
    }

}
