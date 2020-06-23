package User;

import java.sql.SQLException;

public interface UserService {
    UserDTO createUser(UserDTO user) throws Exception;

    public boolean isUserNameValid(String userName) throws Exception;

    public boolean isUserValid(String username, String password, int type) throws Exception;

    public UserDTO getUser(String userName) throws Exception;

    public boolean deleteUser(String username) throws Exception;

    UserDTO[] getRegisteredUser() throws SQLException;

    public boolean changeInformation(UserDTO userDTO, String existingUsername) throws Exception;
}
