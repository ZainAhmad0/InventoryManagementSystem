package Sales;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesDAO {
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException;
    public ResultSet getPreviousSales(String userName) throws SQLException;
}
