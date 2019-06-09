import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test class to test user account class functionality
 *
 *
 **/


public class UserAccountTest {


    private static UserAccount user;


    @Before
    public void setUp() {
        user = new UserAccount(); {
        }
    }

    @Test
    public void userNameTests() throws Exception {


        /*username should have between 6-20 characters,can be upper or lower case
        and include Dashes and spaces inbetween (No digits or other special characters)*/

        try {
            user.setUsername("tomJones1999");
            assertEquals("Should be tomJones1999", "tomJones1999", user.getUsername());
        } catch (InputValidationException e) {
            fail("Should not throw an Exception");
        }


        try {
          user.setUsername("j1mJ0nes");
        }catch (InputValidationException e ) {
            fail("Should not throw an exception");
        }

        try {
            user.setUsername("!kevin");
            fail("Username should start with a letter");
        } catch (InputValidationException e) {
        }

        try {
            user.setUsername("A");
            fail("Should not allow less than two characters");
        } catch (InputValidationException e) {

        }

        try {
            user.setUsername("abcdefghijklmnopqrstxyz");
            fail("Should not be longer than 20 characters");
        } catch (InputValidationException e) {

        }

        try {
            user.setUsername("Marcu.");
            fail("Username should end with a letter or a number");
        } catch (InputValidationException e) {

        }

    }

    @Test
    public void passwordTests() throws Exception {


        /* Password should have between 8-20 characters,can be upper or lower case
        and include digits (No other special characters)*/

        try {
            user.setPassword("S1mpleP4ssword");
            assertEquals("Should be S1mpleP4ssword", "S1mpleP4ssword", user.getPassword());
        } catch (InputValidationException e) {
            fail("Should not throw an Exception");
        }


        try {
            user.setPassword("Passw0rd");
        }catch (InputValidationException e ) {
            fail("Should not throw an exception");
        }

        try {
            user.setPassword("password");
            fail("Password must contain an uppercase letter and atleast one number");
        } catch (InputValidationException e) {
        }

        try {
            user.setPassword("Ah1a2a");
            fail("Should not be less than 8 characters");
        } catch (InputValidationException e) {

        }

        try {
            user.setPassword("abcdefghijklmnopqrstxyz");
            fail("Should not be longer than 20 characters");
        } catch (InputValidationException e) {

        }

        try {
            user.setPassword("AHAH908");
            fail("Password must contain atleast one lowercase letter");
        } catch (InputValidationException e) {

        }

    }




}