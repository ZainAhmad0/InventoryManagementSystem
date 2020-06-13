package Barcode;

import Sales.SalesDTO;
import com.onbarcode.barcode.Code128;

import java.util.ArrayList;
import java.util.Scanner;

public class BarcodeController {
    private ArrayList<Integer> productsReturn = new ArrayList<Integer>();
    private ArrayList<Integer> productQuantitiesReturn = new ArrayList<Integer>();
    private Scanner obj = new Scanner(System.in);


    public void createBarcode(ArrayList<Integer> products) throws Exception {
        Code128 barcode = new Code128();
        for (int i = 0; i < products.size(); i++) {
            barcode.setData(products.get(i).toString());
            barcode.setBarcodeHeight(250);
            barcode.setBarcodeWidth(250);
            barcode.setTextMargin(10);
            barcode.drawBarcode("/media/zain/Data/A/" + products.get(i).toString() + ".png");
        }
        System.out.println("Product Barcodes Generated Successfully");
    }

    public SalesDTO validateBarcodes(SalesDTO salesDTO) {
        int temp;
        System.out.println();
        System.out.println("Please Validate Barcodes By Entering Product Ids In Sequence, Otherwise by entering ");
        System.out.println("wrong barcodes, by default in sequence that product would be removed from the list");
        System.out.println();
        for (int i = 0; i < salesDTO.getProducts().size(); i++) {
            System.out.print("Enter Product Id in Sequence By Scanning Barcode : ");
            temp = obj.nextInt();
            if (temp == salesDTO.getProducts().get(i)) {
                productsReturn.add(temp);
                productQuantitiesReturn.add(salesDTO.getProductQuantity().get(i));
                System.out.println();
                System.out.println("Product Id : " + temp + " Added");
                System.out.println();
            } else {
                System.out.println("Invalid Barcode Entered");
                System.out.println("Product Id : " + salesDTO.getProducts().get(i) + " discarded from products..");
            }
        }
        salesDTO.setProductQuantity(productQuantitiesReturn);
        salesDTO.setProducts(productsReturn);
        return salesDTO;
    }
}
