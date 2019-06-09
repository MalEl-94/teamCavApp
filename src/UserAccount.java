import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 A class representing a general user of the system
 * Member class inherits properties from the User Account class
 *
 *
 * @author Ahad Malik
 **/

public class UserAccount {


    String username;
    String password;


    //Constructor for User account

    UserAccount(){
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) throws InputValidationException {

        if (username.matches("^(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")) {
            this.username = username;
        } else throw new InputValidationException();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws InputValidationException {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
            this.password = password;
        }else throw new InputValidationException();
}


}



