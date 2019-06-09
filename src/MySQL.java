import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class MySQL {

    /**
     * This class is the data layer of the application
     * <p>
     * Saves members and address details to a mySQL database
     * Makes use of various Create, Read, Update and Delete statements (CRUD)
     * Uses parametrised statements for caching speed and also protection against SQL Injection
     *
     * @author Ahad Malik
     **/

    private static final MySQL INSTANCE = new MySQL();

    /**
     * Singleton Design Pattern ensures that there is only one instance of MySQL in the program
     **/

    public static MySQL getInstance() {
        return INSTANCE;
    }


    //Constructors
    public MySQL() {


        // Loads the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try {

            // Make a connection to the "jdbc:mysql://localhost:3306/test" database
            String url = "jdbc:mysql://localhost:3306/test";
            //username and password to access mySQL database
            String username = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, username, password);


            } catch (SQLException e) {
                e.printStackTrace();

        }
    }



    /**
     * Saves a member to the mySQL database
     *
     * @param member  The member to be saved
     * @param address The address to be saved
     **/

    public void saveMember(Member member, Address address) throws Exception {


        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

            //Insert values into Accounts table and return generated memberID
            String SQL = "INSERT INTO Accounts(Username,Password) VALUES (?,?)";
            PreparedStatement userStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            userStatement.setString(1, member.getUsername());
            userStatement.setString(2, member.getPassword());

            // Runs the above SQL statement
            userStatement.executeUpdate();

            //Generated keys (memberID) is extracted and assigned
            ResultSet keys = userStatement.getGeneratedKeys();
            keys.next();
            member.setMemberID(keys.getInt(1));
            userStatement.close();


            //Insert values into Addresses table and return generated addressID
            String AddressSQL = " INSERT INTO Addresses(Street_address,City,Postcode) VALUES (?,?,?)";
            PreparedStatement addressStatement = connection.prepareStatement(AddressSQL, Statement.RETURN_GENERATED_KEYS);

            addressStatement.setString(1, address.getStreetAddress());
            addressStatement.setString(2, address.getCity());
            addressStatement.setString(3, address.getPostcode());

            // Runs the above SQL statement
            addressStatement.executeUpdate();

            //Generated key (addressID) is extracted and assigned
            ResultSet addressKeys = addressStatement.getGeneratedKeys();
            addressKeys.next();
            member.setAddressID(addressKeys.getInt(1));
            addressStatement.close();


            //Finally, insert values into Members table including generated member IDs and Address IDs
            String memberSQL = "INSERT INTO Members(Member_ID,First_name,Last_name,Date_of_birth,Email,Contact_number,Address_ID,WLS_number) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement membersStatement = connection.prepareStatement(memberSQL);

            membersStatement.setInt(1, member.getMemberID());
            membersStatement.setString(2, member.getFirstName());
            membersStatement.setString(3, member.getLastName());
            membersStatement.setString(4, String.valueOf(member.getDob()));
            membersStatement.setString(5, member.getEmail());
            membersStatement.setString(6, member.getContactNumber());
            membersStatement.setInt(7, member.getAddressID());
            membersStatement.setString(8, member.getWlsNumber());

            // Runs the above SQL statement
            membersStatement.executeUpdate();
            membersStatement.close();


            //close connection
          connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
    }

    }

    /**
     * Updates an exsisting members details on the mySQL database
     *
     * @param member;
     **/
    public void updateMember(Member member) {


    }


    /**
     * Deletes a member from the mySQL database
     *
     * @param memberID ID of the member to be deleted
     **/
    public void deleteMember(int memberID) throws Exception {


            //Confirm deletion alert is displayed to admin

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setHeaderText("Confirm");
            confirmAlert.setContentText("Are you sure you want to delete this member?");

            //If OKAY button is pressed
            confirmAlert.showAndWait().filter(ButtonType.OK::equals).ifPresent((b -> {

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

                    //Find member using given ID, if member exists data from all tables is deleted
                    String SQLAcc = "DELETE from Accounts WHERE Member_id = ?";
                    PreparedStatement deleteStatement = connection.prepareStatement(SQLAcc);

                    String SQLMemb = "DELETE from Members WHERE Member_id = ?";
                    PreparedStatement deleteStatementTwo = connection.prepareStatement(SQLMemb);


                    deleteStatement.setInt(1,memberID);
                    deleteStatementTwo.setInt(1,memberID);

                    // Runs the above SQL statement
                    deleteStatement.executeUpdate();
                    deleteStatementTwo.executeUpdate();
                    deleteStatement.close();
                    deleteStatementTwo.close();
                    //close connection
                    connection.close();

                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Delete Successful");
                    errorAlert.setContentText("Member has been deleted from the system");
                    errorAlert.showAndWait();


                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Error");
                    errorAlert.setContentText("Delete unsuccesful");
                    errorAlert.showAndWait();
                }

            }));

    }


    /**
     * Checks if a username entered at regsitration is already taken or not
     *
     * @param username
     **/

    public static boolean checkUsernameExists(String username) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

        boolean usernameExists = false;

        try {

            PreparedStatement st = connection.prepareStatement("SELECT * from Accounts where username = ?");
            st.setString(1, username);
            ResultSet r1 = st.executeQuery();


            if (r1.next()) {
                usernameExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernameExists;
    }



}