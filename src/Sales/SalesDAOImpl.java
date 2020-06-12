package Sales;

import java.util.ArrayList;

public class SalesDAOImpl implements SalesDAO {
    private  static final String INSERT_PRODUCTS = "INSERT INTO InventoryManagementSystem.Sales\n" +
            "(Sales_ID, Customer_Username, Product_ID, Quantity, Purchase_Date)\n" +
            "VALUES({0},''{1}'',{2},{3},''{4}'');";
    @Override
    public void buyProducts(ArrayList<Integer> products,ArrayList<Integer> quantity,String username) {

    }
}
