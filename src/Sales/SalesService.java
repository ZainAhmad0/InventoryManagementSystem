package Sales;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesService {
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException;
    public SalesDTO buyProducts(ArrayList<Integer> products,ArrayList<Integer> quantity,String category) throws SQLException;
}
