package User;

import java.sql.SQLException;

public interface UserService {
    UserDTO createUser(UserDTO user)throws Exception;
    boolean isUserNameValid(String userName) throws Exception;
    boolean isUserValid(String username, String password) throws Exception;
    public UserDTO getUser(String userName) throws Exception;
    boolean deleteUser(String username) throws Exception;
    UserDTO [] getRegisteredUser() throws SQLException;
}
