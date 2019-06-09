public class PasswordEncryption {



    /**
     * This method can be used to generate a string representing an account password
     * suitable for storing in a database.
     *

     *
     * @param password_plaintext The users plaintext password as provided during registration,
     *                           or when changing an account's password.
     * @return String - a string hashed password
     */
    public static void hashPassword(String password_plaintext) {



    }

    /**
     * This method can be used to verify a computed hash from a plaintext
     *
     * @param password_plaintext The account's plaintext password, as provided during a login request
     * @param stored_hash        The account's stored password hash, retrieved from the authorization database
     * @return boolean - true if the password matches the password of the stored hash, false otherwise
     */
    public static void checkPassword(String password_plaintext, String stored_hash) {



    }


}