package User;

import Product.ProductDTO;
import database.DB;

import java.sql.*;
import java.text.MessageFormat;

public class UserDAOImpl implements UserDAO {
    private static final String CREATE = "INSERT INTO InventoryManagementSystem.Users\n" +
            "(userType, firstName, lastName, fatherName, userName, passOfUser, address, mobileNumber)\n" +
            "VALUES({0}, ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'', ''{7}'');\n";
    private  static final String FIND_BY_USERNAME = "SELECT * FROM InventoryManagementSystem.Users WHERE userName = ''{0}''";
    private  static final String GET_REGISTERED_USERS = "SELECT * FROM InventoryManagementSystem.Users WHERE userType = {0}";
    private  static final String DELETE_BY_USERNAME = "DELETE  FROM InventoryManagementSystem.Users WHERE userName = ''{0}'';";
    @Override
    public UserDTO createUser(UserDTO user) throws Exception {
        Connection conn = DB.connectDB();
        String insertQuery = MessageFormat.format(CREATE, user.getUserType(), user.getFirstName(), user.getLastName(),
                user.getFatherName(), user.getUserName(), user.getPassOfUser(), user.getAddress(), user.getMobileNumber());
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        int count = statement.executeUpdate();
        return user;
    }

    @Override
    public UserDTO findByUserName(String username) throws Exception {
        String findUserName = MessageFormat.format(FIND_BY_USERNAME, username);
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(findUserName);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            UserDTO user = new UserDTO();
            user.setUserName(result.getString("userName"));
            user.setFirstName(result.getString("firstName"));
            user.setLastName(result.getString("lastName"));
            user.setFatherName(result.getString("fatherName"));
            user.setPassOfUser(result.getString("passOfUser"));
            user.setAddress(result.getString("address"));
            user.setMobileNumber(result.getString("mobileNumber"));
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(String username) throws SQLException {
        Connection conn = DB.connectDB();
        String deleteQuery = MessageFormat.format(DELETE_BY_USERNAME,username);
        PreparedStatement statement = conn.prepareStatement(deleteQuery);
        statement.executeUpdate();
    }

    @Override
    public UserDTO[] getRegisteredUser() throws SQLException {
        String getUsers = MessageFormat.format(GET_REGISTERED_USERS,0);
        Connection conn = DB.connectDB();
        PreparedStatement statement = conn.prepareStatement(getUsers);
        ResultSet result = statement.executeQuery();
        int rows=0;
        while(result.next()){
            rows++;
        }
        result.beforeFirst();
        int count=0;
        UserDTO[] users = new UserDTO[rows];
        while (result.next()){
            UserDTO temp = new UserDTO();
            temp.setUserName(result.getString("userName"));
            temp.setFirstName(result.getString("firstName"));
            temp.setLastName(result.getString("lastName"));
            temp.setFatherName(result.getString("fatherName"));
            temp.setPassOfUser(result.getString("passOfUser"));
            temp.setAddress(result.getString("address"));
            temp.setMobileNumber(result.getString("mobileNumber"));
            users[count++]=temp;
        }
        return users;
    }
}
