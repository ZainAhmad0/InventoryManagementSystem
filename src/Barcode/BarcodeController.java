package Barcode;

import com.onbarcode.barcode.Code128;

import java.util.ArrayList;
import java.util.Scanner;

public class BarcodeController {
    private ArrayList<Integer> productsReturn = new ArrayList<Integer>();
//    private ArrayList<Integer> productQuantity = new ArrayList<Integer>();
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
   }

   public ArrayList<Integer> validateBarcodes(ArrayList<Integer> products){
       boolean checker = true;
            int temp;
       System.out.println("Please Validate Barcodes...");
       for (int i = 0; i < products.size(); i++) {
           System.out.print("Enter Product Id in Sequence By Scanning Barcode : ");
           temp=obj.nextInt();
           for (int j = i; j < products.size(); j++) {
               if (temp==products.get(j)){
                   productsReturn.add(temp);
                   System.out.println("Product Id : "+temp+" Added..");
                   checker=false;
                   break;
               }
           }
           if (checker){
               System.out.println("Invalid Barcode Entered...");
               System.out.println("Product Id : "+ products.get(i) + " discarded from products..");
               checker=true;
           }
       }
       return productsReturn;
    }
}
