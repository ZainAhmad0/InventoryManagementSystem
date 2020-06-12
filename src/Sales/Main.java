package Sales;

import Barcode.BarcodeController;
import Product.ProductController;

import java.util.ArrayList;

public class Main {
    private static SalesController salesController = new SalesController();
    private static ProductController productController = new ProductController();
    private static BarcodeController barcodeController = new BarcodeController();
    private static SalesDTO salesDTO = new SalesDTO();


    public static void main(String[] args) throws Exception {
        salesDTO = salesController.buyProducts("Zain");
        barcodeController.createBarcode(salesDTO.getProducts());
        salesDTO.setProducts(barcodeController.validateBarcodes(salesDTO.getProducts()));
        salesDTO=salesController.buyProductsAfterBarcodeValidation(salesDTO.getProducts(), salesDTO.getProductQuantity(), "Laptops");
        salesController.addProductRecord(salesDTO,"Zain");
    }
}
