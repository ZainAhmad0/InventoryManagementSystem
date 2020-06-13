package Sales;

import java.util.ArrayList;

public class SalesDTO {
    private ArrayList<SalesProductDTO> productInfo = new ArrayList<SalesProductDTO>();

    public ArrayList<SalesProductDTO> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ArrayList<SalesProductDTO> productInfo) {
        this.productInfo = productInfo;
    }


}
