import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents an address for a member of the club
 *
 *
 **/

public class Address {


    private int addressID;
    private String streetAddress;
    private String city;
    private String postcode;


    //Constructor methods ----------------------------------------------------------------
    public Address(int addressID, String addressLine, String city, String postcode) throws InputValidationException {

        setAddressID(addressID);
        setStreetAddress(addressLine);
        setCity(city);
        setPostcode(postcode);
    }

    public Address() {

    }


    //set+get methods----------------------------------------------------------------

    /**
     * @return current address ID of an address object
     **/
    public int getAddressID() {
        return addressID;
    }
    /**
     * @param addressID to set for an address object
     **/
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }


    /**
     * @return current street address of an address object
     **/
    public String getStreetAddress() {
        return streetAddress;
    }
    /**
     * @param streetAddress to set for an address object
     **/
    public void setStreetAddress(String streetAddress) throws InputValidationException {
        if (streetAddress.matches("^(?:[Pp][Oo]\\s[Bb][Oo][Xx]|[0-9]+)\\s(?:[0-9A-Za-z'#]|[^\\S\\r\\n])+")) {
            this.streetAddress = streetAddress;
        } else throw new InputValidationException();
    }


    /**
     * @return current City of an address object
     **/
    public String getCity() {
        return city;
    }
    /**
     * @param city to set for an address object
     **/
    public void setCity(String city) throws InputValidationException {
        if (city.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$")) {
            this.city = city;
        } else throw new InputValidationException();
    }


    /**
     * @return current post code of an address object
     **/
    public String getPostcode() {
        return postcode;
    }
    /**
     * @param postcode to set for an address object
     **/
    public void setPostcode(String postcode) throws InputValidationException {
        if (valPostcode(postcode)) {
            this.postcode = postcode;
        } else throw new InputValidationException();
    }

    /**
     * Method gets the user input for postcode and tries to match it to a regex pattern
     *
     * @param  postcode to be validated
     *
     * @return true if number is valid, false if not
     **/
    public static boolean valPostcode(String postcode) {

        Pattern patPackageDescription = Pattern.compile("[a-zA-Z]{1,2}[0-9][0-9a-zA-Z]?\\s?[0-9][a-zA-Z][a-zA-Z]");
        Matcher matPackageDescription = patPackageDescription.matcher(postcode);

        while (matPackageDescription.find()) {
            return true;
        }
        return false;
    }


}

























