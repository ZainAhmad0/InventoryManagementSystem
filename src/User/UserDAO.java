package User;

import java.sql.SQLException;

public interface UserDAO {
    UserDTO createUser(UserDTO user)throws Exception;
    UserDTO findByUserName(String username)throws Exception;
    void deleteUser(String username) throws SQLException;
    UserDTO [] getRegisteredUser() throws SQLException;
    public void changeInformation(UserDTO userDTO,String existingUsername) throws SQLException;
}
