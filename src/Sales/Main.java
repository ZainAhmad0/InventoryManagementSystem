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
        productController.showProductsByCategory("Men Clothes");
        salesDTO = salesController.buyProducts("Zain");
        salesDTO = salesController.validateProducts(salesDTO, "Men Clothes");
        barcodeController.createBarcode(salesDTO);
        salesDTO = barcodeController.validateBarcodes(salesDTO);
        salesDTO = salesController.validateProductInStock(salesDTO);
        salesController.addProductRecord(salesDTO, "Zain");
        salesController.generateReceipt(salesDTO, "Zain Ahmad");
//        productController.showProductsByCategory("Laptops");
//        salesDTO = salesController.buyProducts("Zain");
//        salesDTO=salesController.validateProducts(salesDTO, "Laptops");
//        barcodeController.createBarcode(salesDTO);
//        salesDTO= barcodeController.validateBarcodes(salesDTO);
//        salesDTO=salesController.validateProductInStock(salesDTO);
//        salesController.addProductRecord(salesDTO,"Zain");
        // salesController.showPreviousSales("Zain");
    }
}
