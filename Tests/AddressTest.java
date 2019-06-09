import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class AddressTest {

    private static Address testAddress;


    @Before
    public void setUp() {
        testAddress = new Address();
    }


    @Test
    public void streetAddressTests() throws Exception {

        try {
            testAddress.setStreetAddress("10 Maplewood park");
        } catch (InputValidationException e) {
            fail("Valid Address");
        }

        try {
            testAddress.setStreetAddress("58 Ashley Street");
        } catch (InputValidationException e) {
            fail("Valid Address");
        }

        try {
            testAddress.setStreetAddress("Ashley Street");
            fail("Invalid Address");
        } catch (InputValidationException e) {

        }

        try {
            testAddress.setStreetAddress("28 ");
            fail("Invalid Address");
        } catch (InputValidationException e) {

        }



    }

    @Test
    public void cityTests() throws Exception {


        //Test valid city/town names

        try {
            testAddress.setCity("Glasgow");
            assertEquals("Should be Glasgow", "Glasgow", testAddress.getCity());
        } catch (InputValidationException e) {
            fail("Valid City name, should not throw exception");
        }

        try {
            testAddress.setCity("St Andrews");

        } catch (InputValidationException e) {
            fail("Valid City name, should not throw exception");
        }

        try {
            testAddress.setCity("Ayr");

        } catch (InputValidationException e) {
            fail("Valid City name, should not throw exception");
        }

        //Test for spaces at start
        try {
            testAddress.setCity(" Edinburgh");
            fail("Space at start of city name");
        } catch (InputValidationException e) {
        }

        //Test for Digits & Bogus characters

        try {
            testAddress.setCity("L1vingston");
            fail("Digit within city name");
        } catch (InputValidationException e) {
        }

        try {
            testAddress.setCity("P@!sl3y");
            fail("Multiple Bogus characters");
        } catch (InputValidationException e) {
        }


    }


    @Test
    public void postcodeTests() throws Exception {


//Test valid uk postcodes (can contain 5-7 Alphanumeric characters)

        //full caps with a space
        try {
            testAddress.setPostcode("EH54 8BB");
            assertEquals("Should be EH54 8BB", "EH54 8BB", testAddress.getPostcode());
        } catch (InputValidationException e) {
            fail("Valid Postcode name, should not throw exception");
        }

        //lower caps
        try {
            testAddress.setPostcode("g49eu");
        } catch (InputValidationException e) {
            fail("Valid Postcode, should not throw exception");
        }

        try {
            testAddress.setPostcode("RG10 9QX");
        } catch (InputValidationException e) {
            fail("Valid Postcode, should not throw exception");
        }

        try {
            testAddress.setPostcode("DN55 1PT");
        } catch (InputValidationException e) {
            fail("Valid Postcode, should not throw exception");
        }

        //starting with a number
        try {
            testAddress.setPostcode("3KGJ4");
            fail("Invalid Postcode, Cannot start with a number");
        } catch (InputValidationException e) {
        }





    }

}