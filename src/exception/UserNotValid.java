package exception;

import User.UserDTO;

public class UserNotValid extends RuntimeException{
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public UserNotValid(String message) {
        super(message);
        this.message = message;
    }
}
