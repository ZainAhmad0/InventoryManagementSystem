package Barcode;

import Sales.SalesDTO;
import Sales.SalesProductDTO;
import com.onbarcode.barcode.Code128;
import java.util.ArrayList;
import java.util.Scanner;
public class BarcodeController {
    private Scanner obj = new Scanner(System.in);
    private ArrayList<SalesProductDTO> tempList = new ArrayList<SalesProductDTO>();



    public void createBarcode(SalesDTO salesDTO) throws Exception {
        Code128 barcode = new Code128();
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            barcode.setData(salesDTO.getProductInfo().get(i).getProductID().toString());
            barcode.setBarcodeHeight(250);
            barcode.setBarcodeWidth(250);
            barcode.setTextMargin(10);
            barcode.drawBarcode("/media/zain/Data/A/" + salesDTO.getProductInfo().get(i).getProductID().toString() + ".png");
        }
        System.out.println("Product Barcodes Generated Successfully");
    }

    public SalesDTO validateBarcodes(SalesDTO salesDTO) {
        int temp;
        System.out.println();
        System.out.println("Please Validate Barcodes By Entering Product Ids In Sequence, Otherwise by entering ");
        System.out.println("wrong barcodes, by default in sequence that product would be removed from the list");
        System.out.println();
        for (int i = 0; i < salesDTO.getProductInfo().size(); i++) {
            System.out.print("Enter Product Id in Sequence By Scanning Barcode : ");
            temp = obj.nextInt();
            if (temp == salesDTO.getProductInfo().get(i).getProductID()) {
                SalesProductDTO salesProductDTO = new SalesProductDTO();
                salesProductDTO.setProductID(temp);
                salesProductDTO.setQuantity(salesDTO.getProductInfo().get(i).getQuantity());
                tempList.add(salesProductDTO);
                System.out.println();
                System.out.println("Product Id : " + temp + " Added");
                System.out.println();
            } else {
                System.out.println("Invalid Barcode Entered");
                System.out.println("Product Id : " + salesDTO.getProductInfo().get(i).getProductID() + " discarded from products..");
            }
        }
        salesDTO.setProductInfo(tempList);
        return salesDTO;
    }
}
