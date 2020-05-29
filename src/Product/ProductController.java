package Product;

import exception.ObjectNotFound;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    private int productID;
    private String productName,category;
    private Double salesPrice,purchasePrice;
    private final ProductService productService = new ProductServiceImp();
    private static Scanner obj = new Scanner(System.in);
    void addProduct() {
        try {
            System.out.print("Enter Product Name : ");
            productName = obj.nextLine();
            System.out.print("Enter Product Category : ");
            category = obj.nextLine();
            System.out.print("Enter Product Sales Price : ");
            salesPrice = obj.nextDouble();
            System.out.print("Enter Product Purchase Price : ");
            purchasePrice = obj.nextDouble();
            ProductDTO product = new ProductDTO();
            product.setCategory(category);
            product.setProductName(productName);
            product.setPurchasePrice(purchasePrice);
            product.setSalesPrice(salesPrice);
            productService.addProduct(product);
        }catch (ObjectNotFound e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    void updateProductName() throws SQLException {
        System.out.print("Enter Product Name : ");
        productName=obj.nextLine();
        String newProductName = new String();
        System.out.print("Enter New Product Name : ");
        newProductName=obj.nextLine();
        ProductDTO product = new ProductDTO();
        product.setProductName(newProductName);
        productService.updateProduct(productName,product);
    }
    void updateProductCategory() throws SQLException {
        System.out.print("Enter Product Name : ");
        productName=obj.nextLine();
        System.out.print("Enter New Product Category : ");
        category=obj.nextLine();
        ProductDTO product = new ProductDTO();
        product.setCategory(category);
        productService.updateProduct(productName,product);
    }
    void updateProductSalesPrice() throws SQLException {
        System.out.print("Enter Product Name : ");
        productName=obj.nextLine();
        System.out.print("Enter New Sales Price of the Product : ");
        salesPrice=obj.nextDouble();
        ProductDTO product = new ProductDTO();
        product.setSalesPrice(salesPrice);
        productService.updateProduct(productName,product);
    }

    public void showTable(String tableName) throws SQLException {
        ProductDTO[] products=new ProductDTO[getNumberOfRows(tableName)];
        products=productService.showTable(tableName);
        System.out.println("ID          Name          Price          Category");
        for(int i=0; i<products.length; i++){
            System.out.println(products[i].getProductID()+"          "+products[i].getProductName()+
                    "          "+products[i].getSalesPrice()+"          "+products[i].getCategory());
        }
    }

    public void showProductsByCategory(String category) throws SQLException {
        ProductDTO[] products=productService.findProductsByCategory(category);
        System.out.println("ID          Name          Price          Category");
        for(int i=0; i<products.length; i++){
            System.out.println(products[i].getProductID()+"          "+products[i].getProductName()+
                    "          "+products[i].getSalesPrice()+"          "+products[i].getCategory());
        }

    }

    private int getNumberOfRows(String tableName) throws SQLException {
        return productService.getNumOfProducts(tableName);
    }
}
