package Sales;
import java.util.ArrayList;

public class SalesDTO {
    private  ArrayList<Integer> products = new ArrayList<Integer>();
    private  ArrayList<Integer> productQuantity = new ArrayList<Integer>();

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public  void setProductQuantity(ArrayList<Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public  ArrayList<Integer> getProducts() {
        return products;
    }

    public  ArrayList<Integer> getProductQuantity() {
        return productQuantity;
    }

}
