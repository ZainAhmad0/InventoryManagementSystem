package Sales;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesService {
    public void buyProducts(ArrayList<Integer> products,ArrayList<Integer> quantity,String category,String username) throws SQLException;
}
