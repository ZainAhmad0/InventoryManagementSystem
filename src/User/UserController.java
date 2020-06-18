package User;

import exception.UserNotValid;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
private final UserService userService = new UserServiceImp();
private String firstName,lastName,fatherName,userName,passOfUser,address,mobileNumber;
Scanner obj = new Scanner(System.in);
    public boolean registerUser() {
        try {
            System.out.print("Enter First Name : ");
            firstName = obj.nextLine();
            System.out.print("Enter Last Name : ");
            lastName = obj.nextLine();
            System.out.print("Enter Father Name : ");
            fatherName = obj.nextLine();
            System.out.print("Enter User Name : ");
            userName = obj.nextLine();
            System.out.print("Enter password : ");
            passOfUser = obj.nextLine();
            System.out.print("Enter address : ");
            address = obj.nextLine();
            System.out.print("Enter mobile number : ");
            mobileNumber = obj.nextLine();
            UserDTO user = new UserDTO();
            user.setFirstName(firstName);
            user.setAddress(address);
            user.setFatherName(fatherName);
            user.setLastName(lastName);
            user.setMobileNumber(mobileNumber);
            user.setPassOfUser(passOfUser);
            user.setUserName(userName);
            userService.createUser(user);
        }catch (UserNotValid e){
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return true;
    }

    public boolean isUserValid(String username, String password,int type) throws Exception {
        return userService.isUserValid(username,password,type);
    }

    public boolean deleteUser(String username) throws Exception {
    return userService.deleteUser(username);
    }

    public void getRegisteredUser() throws SQLException {
        UserDTO users[];
        users=userService.getRegisteredUser();
        System.out.println(" Username                    Password");
        for (int i=0; i<users.length; i++){
            System.out.println((i+1)+" "+ users[i].getUserName() + "               " +users[i].getPassOfUser());
        }
    }
}
