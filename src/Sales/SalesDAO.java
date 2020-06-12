package Sales;
import java.util.ArrayList;

public interface SalesDAO {
    public void buyProducts(ArrayList<Integer> products,ArrayList<Integer> quantity,String username);
}
