import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a member of the club
 * Allowing members to login to view training programmes and use other features
 *
 * Extends User account
 *
 * @author Ahad Malik
 **/


public class Member extends UserAccount {


    private int memberID;
    private String firstName;
    private String lastName;
    private String dob;
    private String contactNumber;
    private int addressID;
    private String email;
    private String wlsNumber;


    /*
     Member class constructor methods
    */

    public Member(int memberID, String firstName, String lastName, String dob, String contactNumber, int addressID, String wlsNumber,
                  String username, String password) throws Exception {

        setMemberID(memberID);
        setFirstName(firstName);
        setLastName(lastName);
        setDob(dob);
        setContactNumber(contactNumber);
        setAddressID(addressID);
        setWlsNumber(wlsNumber);
        setUsername(username);
        setPassword(password);

    }


    public Member(int memberID, String firstName, String lastName, String dob, String email, String wlsNumber) throws Exception {

        setMemberID(memberID);
        setFirstName(firstName);
        setLastName(lastName);
        setDob(dob);
        setEmail(email);
        setWlsNumber(wlsNumber);
    }

    public Member(String username) throws InputValidationException {
        setUsername(username);
    }


    public Member() {
    }


    /**
     * @return current member ID of a member object
     **/
    public int getMemberID() {
        return memberID;
    }

    /**
     * @param memberID to set for member object
     **/
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }


    /**
     * @return current first name of a member object
     **/
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName to set for member object
     **/
    public void setFirstName(String firstName) throws InputValidationException {

        if (firstName.matches("^(?=.{2,20}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$")) {
            this.firstName = firstName;
        } else throw new InputValidationException();
    }


    /**
     * @return current last name  of a member object
     **/
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName to set for member object
     **/
    public void setLastName(String lastName) throws InputValidationException {

        if (lastName.matches("^(?=.{2,20}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$")) {
            this.lastName = lastName;
        } else throw new InputValidationException();
    }


    /**
     * @return current date of birth of a member object
     **/
    public String getDob() {
        return dob;
    }

    /**
     * @param dob to set for member object
     **/
    public void setDob(String dob) throws ParseException, InputValidationException {

        if (valDob(dob)) {
            this.dob = dob;
        } else throw new InputValidationException();
    }

    /**
     * @return current contact number of a member object
     **/
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber to set for member object
     **/
    public void setContactNumber(String contactNumber) throws InputValidationException {

        if (valNumber(contactNumber)) {
            this.contactNumber = contactNumber;
        } else throw new InputValidationException();
    }


    /**
     * @return current address ID of a member object
     **/
    public int getAddressID() {
        return addressID;
    }
    /**
     * @param addressID to set for member object
     **/
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }


    /**
     * @return current email address of a member object
     **/
    public String getEmail() {
        return email;
    }
    /**
     * @param email to set for member object
     **/
    public void setEmail(String email) throws InputValidationException {

        if (email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            this.email = email;
        } else throw new InputValidationException();
    }


    /**
     * @return current wls number of a member object
     **/
    public String getWlsNumber() {
        return wlsNumber;
    }
    /**
     * @param wlsNumber to set for member object
     **/
    public void setWlsNumber(String wlsNumber) throws InputValidationException {
        if (wlsNumber.matches("^(?:[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$")) {
            this.wlsNumber = wlsNumber;
        } else throw new InputValidationException();
    }



    /**
     * Method gets the user input for date of birth, and tries and convert it to a simpledateformat.
     *
     * @param  dob (date of birth) to be validated
     *
     * @return true if date is valid, false if not
     **/
    public static boolean valDob(String dob) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            df.parse(dob);

        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Method gets the user input for contactnumber and tries to match it to a regex pattern
     *
     * @param  contactNumber to be validated
     *
     * @return true if number is valid, false if not
     **/
    public static boolean valNumber(String contactNumber) {

        Pattern patPackageDescription = Pattern.compile("^(0044|0|\\+?44)[12378]\\d{8,9}");
        Matcher matPackageDescription = patPackageDescription.matcher(contactNumber);

        while (matPackageDescription.find()) {

            return true;
        }

        return false;
    }


}