package Barcode;
import java.util.*;
public class Main {
    private static BarcodeController obj = new BarcodeController();
    private static ArrayList<Integer> products = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        products.add(2);
        products.add(4);
        products.add(6);
        products.add(8);
        obj.createBarcode(products);
    }

}
