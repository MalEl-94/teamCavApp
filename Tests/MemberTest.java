import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import static org.junit.Assert.*;


/**
 * Test class to test member class functionality
 *
 *
 **/

public class MemberTest {

    private static Member testMember;


    @Before
    public void setUp() {
        testMember = new Member();
    }


    /*First name should have between 2-20 characters,can be upper or lower case
            and include Dashes and spaces inbetween (No digits or other special characters)*/
    @Test
    public void firstNameTests() throws Exception {

        try {
            testMember.setFirstName("Keanu");
            assertEquals("Should be Keanu", "Keanu", testMember.getFirstName());
        } catch (InputValidationException e) {
            fail("Should not throw an Exception");
        }


        try {
            testMember.setFirstName("George Bernard");
            }catch (InputValidationException e ) {
            fail("Should not throw an exception");
            }

        try {
            testMember.setFirstName("1Kevin");
            fail("Name should start with a letter");
        } catch (InputValidationException e) {
        }

        try {
            testMember.setFirstName("A");
            fail("Should not allow less than two characters");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setFirstName("abcdefghijklmnopqrstxyz");
            fail("Should not be longer than 20 characters");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setFirstName("Marcu$");
            fail("Should not include special characters");
        } catch (InputValidationException e) {

        }

    }


    /*Last name should have between 2-20 characters,can be upper or lower case
   and include Dashes and spaces inbetween (No digits or other special characters)*/
    @Test
    public void lastNameTests() throws Exception {




        try {
            testMember.setLastName("Reeves");
            assertEquals("Should be Reeves", "Reeves", testMember.getLastName());
        } catch (InputValidationException e) {
            fail("Should not throw an Exception");
        }


        try {
            testMember.setLastName("1Bacon");
            fail("Name should start with a letter");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setLastName("A");
            fail("Should not allow less than two characters");
        } catch (InputValidationException e) {

        }

            try {
                testMember.setLastName("Kal-El");
            } catch (InputValidationException e) {
                fail("Should not throw exception");
        }

        try {
            testMember.setLastName("abcdefghijklmnopqrstxyz");
            fail("Should not be longer than 20 characters");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setLastName("$");
            fail("Should not include special charcaters");
        } catch (InputValidationException e) {

        }


    }


    //Date of birth should be in dd/mm/yyyy format and cannot include invalid dates
    @Test
    public void dateOfBirthTests() throws Exception {


        try {
            testMember.setDob("12/11/1993");
            assertEquals("Should be 12/11/1993", "12/11/1993", testMember.getDob());
        } catch (Exception e) {
            fail("Should not throw an Exception");
        }

        try {
            testMember.setDob("10/13/1990");
            fail("Should not be allowed");
        } catch (Exception e) {
        }

        try {
            testMember.setDob("31/02/2001");
            fail("Should not be allowed");
        } catch (Exception e) {
        }

        try {
            testMember.setDob("01.02.2020");
            fail("Should not be allowed");
        } catch (Exception e) {
        }


    }

  /*
  * Test Contact Number ith valid UK Numbers starting with:
   0044
   +44
   44
   0
   Then the numbers thereafter to be between 9 and 10 digits in length, with the first of these digits being either: 1,2,3,7,8 **/
 @Test
    public void contactNumberTests() throws Exception {


        try {
            testMember.setContactNumber("07577979385");
            assertEquals("Should be 07577979385", "07577979385", testMember.getContactNumber());
        } catch (Exception e) {
            fail("Should not throw an Exception");
        }

        try {
            testMember.setContactNumber("+441234567890");

        } catch (InputValidationException e) {
            fail("Valid number,should not throw an Exception");
        }

        try {
            testMember.setContactNumber("0777A567832");
            fail("Invalid number");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setContactNumber("-44123456i890");
            fail("Invalid number");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setContactNumber("1111111");
            fail("Invalid number");
        } catch (InputValidationException e) {

        }



    }


    //Email address should be in a valid format with the correct extensions used
    @Test
    public void emailTests() throws Exception {

        try {
            testMember.setEmail("ahadm94@hotmail.co.uk");
            assertEquals("ahadm94@hotmail.co.uk", "ahadm94@hotmail.co.uk", testMember.getEmail());
        } catch (InputValidationException e) {
            fail("Valid email,should not throw an Exception");
        }

        //Test email extensions
        try {
            testMember.setEmail("ahadm94@hotmail.org");
        } catch (InputValidationException e) {
            fail("Valid email,should not throw an Exception");
        }

        try {
            testMember.setEmail("ahadm94@hotmail.com");
        } catch (InputValidationException e) {
            fail("Valid email,should not throw an Exception");
        }

        try {
            testMember.setEmail("ahadm94@hotmail.net");
        } catch (InputValidationException e) {
            fail("Valid email,should not throw an Exception");
        }

        try {
            testMember.setEmail("ahadm94@hotmail.");
            fail("Invalid email");
        } catch (InputValidationException e) {

        }

        //Test email with dash

        try {
            testMember.setEmail("andy.noble@data-workshop.com");

        } catch (InputValidationException e) {
            fail("Valid email,should not throw an Exception");
        }

        try {
            testMember.setEmail("andy-noble@data-workshop.-com");
            fail("Invalid email");
        } catch (InputValidationException e) {

        }

        //Test email with commas
        try {
            testMember.setEmail("joeblow@apa,che.org");
            fail("Invalid email, has commas");
        } catch (InputValidationException e) {
        }

        //Test email with spaces
        try {
            testMember.setEmail("joeblow@apa che.org");
            fail("Invalid email, has spaces");
        } catch (InputValidationException e) {
        }

        //Test email with bogus character

        try {
            testMember.setEmail("andy@o'reilly.data-workshop.com");
            fail("Invalid email, has a bogus character");
        } catch (InputValidationException e) {
        }
        try {
            testMember.setEmail("foo+bar@example+3.com");
            fail("Invalid email, has a bogus character");
        } catch (InputValidationException e) {
        }


    }

    //WLS number can range from 1-9999
    @Test
    public void wlsNumberTests() throws Exception {

        //Test with correct inputs
        try {
            testMember.setWlsNumber("702");

        } catch (InputValidationException e) {
            fail("Valid number");
        }

        try {
            testMember.setWlsNumber("93");

        } catch (InputValidationException e) {
            fail("Invalid number");
        }

        //Boundry inputs
        try {
            testMember.setWlsNumber("1");

        } catch (InputValidationException e) {
            fail("Invalid number");
        }

        try {
            testMember.setWlsNumber("9999");

        } catch (InputValidationException e) {
            fail("Invalid number");
        }

        //Test with incorrect inputs

        try {
            testMember.setWlsNumber("0045");
            fail("Invalid number");
        } catch (InputValidationException e) {

        }
        try {
            testMember.setWlsNumber("10000");
            fail("Invalid number");
        } catch (InputValidationException e) {

        }

        try {
            testMember.setWlsNumber("ewda");
            fail("Invalid number");
        } catch (InputValidationException e) {
        }










    }

    @Test
    public void constructorTests() throws Exception {
        try {
            testMember = new Member(01,"Kevin","Bacon","24/12/1994",
                    "01596462162",232,"103","amalik","kevinbaconisG0D");

            assertEquals("Should be Kevin", "Kevin", testMember.getFirstName());

            assertEquals("Should be Bacon", "Bacon", testMember.getLastName());

            assertEquals("Should be 24/12/1994", "24/12/1994", testMember.getDob());

            assertEquals("Should be 01596462162", "01596462162", testMember.getContactNumber());

            assertEquals("Should be 103", "103", testMember.getWlsNumber());
        }
        catch (InputValidationException e) {
            fail("Should not throw exception");
        }

    }


}