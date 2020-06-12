package Sales;

import Product.ProductController;

public class Main {
    private static SalesController salesController = new SalesController();
    private static ProductController productController = new ProductController();

    public static void main(String[] args) throws Exception {
        salesController.buyProducts("asdf");

    }
}
