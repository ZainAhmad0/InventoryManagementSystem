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
        salesDTO=salesController.validateProducts(salesDTO.getProducts(), salesDTO.getProductQuantity(), "Laptops");
        barcodeController.createBarcode(salesDTO.getProducts());
        salesDTO= barcodeController.validateBarcodes(salesDTO);
        salesController.addProductRecord(salesDTO,"Zain");
    }
}
