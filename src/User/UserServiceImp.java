package User;

import exception.ObjectNotFound;
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
        if (!isUserNameValid(username)) {
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
    public boolean changeInformation(UserDTO userDTO, String existingUsername) throws Exception {
        UserDTO userDTO1 = userDAO.findByUserName(existingUsername);
        if (userDTO.getPassOfUser() != null) {
            userDTO1.setPassOfUser(userDTO.getPassOfUser());
            userDAO.changeInformation(userDTO1, existingUsername);
            return true;
        } else if (isUserNameValid(userDTO.getUserName())) {
            userDTO1.setUserName(userDTO.getUserName());
            userDAO.changeInformation(userDTO1, existingUsername);
            return true;
        } else {
            throw new ObjectNotFound(userDTO.getUserName() + " Username Already Exists");
        }

    }

    @Override
    public boolean isUserValid(String username, String password, int type) throws Exception {
        UserDTO user = new UserDTO();
        if (!(isUserNameValid(username))) {
            user = getUser(username);
            return user.getPassOfUser().equals(password) && user.getUserType() == type;
        }
        return false;
    }
}