package Sales;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesDAO {
    public void addProductRecord(ArrayList<Integer> products,ArrayList<Integer> productQuantities, String username) throws SQLException;
}
