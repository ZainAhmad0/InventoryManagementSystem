package Sales;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesDAO {
    public void addProductRecord(SalesDTO salesDTO, String username) throws SQLException;
}
