package User;
import exception.UserNotValid;

import java.sql.SQLException;
import java.text.MessageFormat;

public class UserServiceImp implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public UserDTO createUser(UserDTO user) throws Exception {
        user.setUserType(0);
        if (isUserNameValid(user.getUserName())) {
            return userDAO.createUser(user);
        } else {
            throw new UserNotValid(MessageFormat.format("''{0}'' This username already exists", user.getUserName()));
        }
    }

    public boolean isUserNameValid(String userName) throws Exception {
        UserDTO output = userDAO.findByUserName(userName);
        return (output == null);
    }

    public UserDTO getUser(String userName) throws Exception {
        return userDAO.findByUserName(userName);
    }

    @Override
    public boolean deleteUser(String username) throws Exception {
        if(!isUserNameValid(username)){
            userDAO.deleteUser(username);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO[] getRegisteredUser() throws SQLException {
        return userDAO.getRegisteredUser();
    }

    @Override
    public boolean isUserValid(String username, String password) throws Exception {
        UserDTO user = new UserDTO();
        if (!(isUserNameValid(username))) {
            user = getUser(username);
        }
        return user.getPassOfUser().equals(password);
    }
}