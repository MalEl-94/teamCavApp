import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.ResourceBundle;


/**
 * This class is the controller class
 * for the Edit details fxml view
 *
 *
 *
 **/
public class EditDetailsController implements Initializable {


    @FXML
    private TableView memberTable, addressTable;
    @FXML
    private Button bckBtn, updateBtn;
    @FXML
    private TableColumn firstNameCol, lastNameCol, dateOfBirthCol, contactNumberCol, emailCol, stAddressCol, cityCol, pCodeCol;
    @FXML
    private TextField fNameTxtField, lNameTxtField, dobTxtField, contactNumbTxtField, sAddressTxtField, cityTxtField, pCodeTxtField,
            wlsNumbTxtField, uNameTxtField, emailTxtField;
    @FXML
    private PasswordField passwordField, confPasswordField;


    private ObservableList<Member> memberData;
    private ObservableList<Address> addressData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        buildData();

        //load member data to member tableview
        memberTable.setItems(null);
        memberTable.setItems(memberData);
        //load address data to addresses tableview
        addressTable.setItems(null);
        addressTable.setItems(addressData);


     /*
      Event listener / controller for the Update button

    */
        updateBtn.setOnAction(event -> {

            Boolean noinput = true;


          if(!fNameTxtField.getText().trim().isEmpty()) {

              noinput = false;

              try {

                  changeFirstName();

              } catch (Exception e) {

                  Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                  infoAlert.setHeaderText("Update Unsuccessful");
                  infoAlert.setContentText("First name could not be updated");
                  infoAlert.showAndWait();
              }
          }


            if(!lNameTxtField.getText().trim().isEmpty()) {

                noinput = false;

                try {

                    changeLastName();

                } catch (Exception e) {

                    Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                    infoAlert.setHeaderText("Update Unsuccessful");
                    infoAlert.setContentText("Last name could not be updated");
                    infoAlert.showAndWait();
                }
            }


            if(!dobTxtField.getText().trim().isEmpty()) {

                noinput = false;

                try {
                    changeDateOfBirth();


                } catch (Exception e) {

                    Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                    infoAlert.setHeaderText("Update Unsuccessful");
                    infoAlert.setContentText("Date of birth could not be updated");
                    infoAlert.showAndWait();
                }
            }


            if(!contactNumbTxtField.getText().trim().isEmpty()) {

                noinput = false;

                try {
                    changeContactNumber();

                } catch (Exception e) {

                    Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                    infoAlert.setHeaderText("Update Unsuccessful");
                    infoAlert.setContentText("Contact number could not be updated");
                    infoAlert.showAndWait();
                }
            }

            if(!contactNumbTxtField.getText().trim().isEmpty()) {

                noinput = false;

                try {
                    changeContactNumber();


                } catch (Exception e) {

                    Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                    infoAlert.setHeaderText("Update Unsuccessful");
                    infoAlert.setContentText("Contact number could not be updated");
                    infoAlert.showAndWait();
                }
            }



            if(noinput){

                Alert infoAlert = new Alert(Alert.AlertType.ERROR);
                infoAlert.setHeaderText("No input deteted");
                infoAlert.showAndWait();
            }



        });

 /*
      Event listener / controller for the back button

    */
        bckBtn.setOnAction(event -> {

            try {

                Parent dashParent = FXMLLoader.load(getClass().getResource("FXML FILES/Dashboard.fxml"));
                Scene regScene = new Scene(dashParent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                app_stage.setScene(regScene);
                app_stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    /**
     *
     * Method loads logged in users data from the database
     *
     **/
    public void buildData() {

        //Data retrieved is stored in an observable array list
        memberData = FXCollections.observableArrayList();
        addressData = FXCollections.observableArrayList();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");


            //Query to select all members using username entered at login
            String SQL = "SELECT * FROM accounts,members,addresses where Username = " + LoginController.newLogin.getMemberID() ;
            PreparedStatement st = connection.prepareStatement(SQL);

            ResultSet rs = st.executeQuery(SQL);
            //extract data from result set and assign to a new member object
            while (rs.next()) {

                memberData.add(new Member(rs.getInt("Member_ID"), rs.getString("First_name"), rs.getString("Last_name"),
                        rs.getString("Date_of_birth"), rs.getString("Email"), rs.getString("WLS_number")));

                addressData.add(new Address(rs.getInt("Address_ID"), rs.getString("Street_address"), rs.getString("City"),
                        rs.getString("Postcode")));
            }

            //close connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (InputValidationException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Member details could not be loaded from the database");
            errorAlert.showAndWait();;
        }


        //Set cell value factory for member table
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactNumberCol.setCellValueFactory(new PropertyValueFactory<>("Contact_number"));

        //Set cell value factory for address table
        stAddressCol.setCellValueFactory(new PropertyValueFactory<>("Street_address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
        pCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postcode"));


    }

    /*
        Method is used when data is entered into the first name field and the update button is clicked
         Used to validate input and change the last name of a member on the database
                                                                                                  */
    public void changeFirstName() {


        try {

            //Make connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

            if (!fNameTxtField.getText().trim().isEmpty()) {

                //Get and set first name of member object that was created during the data build
                memberData.get(0).setFirstName(fNameTxtField.getText());

                //Create  java preparedstatement using a sql update query
                String SQL = "UPDATE members SET First_name = ?  WHERE Member_ID = ?";
                PreparedStatement ps = connection.prepareStatement(SQL);

                ps.setString(1, memberData.get(0).getFirstName());
                ps.setInt(2, memberData.get(0).getMemberID());

                // call executeUpdate to execute sql update statement
                ps.executeUpdate();
                ps.close();
                connection.close();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setHeaderText("Update Successful");
                infoAlert.setContentText("First name has been updated");
                infoAlert.showAndWait();


            }
        } catch (InputValidationException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input error");
            errorAlert.setContentText("First name must be  2 -20 characters long and cannot contain numbers or special characters");
            errorAlert.showAndWait();
        } catch (SQLException e) {
        } catch (Exception e) {
        }

    }


    /*
        Method is used when data is entered into the last name field and the update button is clicked
        Used to input and change the last name of a member on the database
                                                                                                      */
    public void changeLastName() {


        try {

            //Make a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

            if (!lNameTxtField.getText().trim().isEmpty()) {

                //Get and set first name of member object that was created during the data build
                memberData.get(0).setLastName(lNameTxtField.getText());

                //Create  java preparedstatement using a sql update query
                String SQL = "UPDATE members SET Last_name = ?  WHERE Member_ID = ?";
                PreparedStatement ps = connection.prepareStatement(SQL);

                ps.setString(1, memberData.get(0).getLastName());
                ps.setInt(2, memberData.get(0).getMemberID());

                // call executeUpdate to execute sql update statement
                ps.executeUpdate();
                ps.close();
                connection.close();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setHeaderText("Update Successful");
                infoAlert.setContentText("Last name has been updated");
                infoAlert.showAndWait();

            }
        } catch (InputValidationException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input error");
            errorAlert.setContentText("Last name must be  2 -20 characters long and cannot contain numbers or special characters");
            errorAlert.showAndWait();
        } catch (SQLException e) {
        } catch (Exception e) {
        }

    }

    /*
        Method is used when data is entered into the DoB field and the update button is clicked
        Used to validate input and change the date of birth of a member on the database
                                                                                                  */
    public void changeDateOfBirth() {

        /*
        Method is used when data is entered into the date of birth field and the update button is clicked
         Used to change the validate input and change the date of birth of a member on the database

                                                                                                     */
        try {

            if (!dobTxtField.getText().trim().isEmpty()) {

                //Get and set first name of member object that was created during the data build
                memberData.get(0).setDob(dobTxtField.getText());

                //Make connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

                //Create  java preparedstatement using a sql update query
                String SQL = "UPDATE members SET Date_of_birth = ?  WHERE Member_ID = ?";
                PreparedStatement ps = connection.prepareStatement(SQL);

                ps.setString(1, memberData.get(0).getDob());
                ps.setInt(2, memberData.get(0).getMemberID());

                // call executeUpdate to execute sql update statement
                ps.executeUpdate();
                ps.close();

                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setHeaderText("Update Successful");
                infoAlert.setContentText("Date of birth has been updated");
                infoAlert.showAndWait();
            }
        } catch (ParseException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input error");
            errorAlert.setContentText("Please ensure the date of birth in the correct format (dd/mm/yyyy)");
            errorAlert.showAndWait();
        } catch (SQLException e) {
        } catch (Exception e) {
        }

    }

    /*
       Method is used when data is entered into the contact number and the update button is clicked
       Used to validate input and change the contact number a member on the database
                                                                                              */
    public void changeContactNumber() {

        /*
        Method is used when data is entered into the last name field and the update button is clicked
         Used to change the validate input and change the last name of a member on the database
                                                 */
        try {

            if (!contactNumbTxtField.getText().trim().isEmpty()) {

                //Get and set first name of member object that was created during the data build
                memberData.get(0).setLastName(lNameTxtField.getText());

                //Make connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
                //Create  java preparedstatement using a sql update query
                String SQL = "UPDATE members SET Contact_number = ?  WHERE Member_ID = ?";
                PreparedStatement ps = connection.prepareStatement(SQL);

                ps.setString(1, memberData.get(0).getContactNumber());
                ps.setInt(2, memberData.get(0).getMemberID());

                // call executeUpdate to execute sql update statement
                ps.executeUpdate();
                ps.close();


                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setHeaderText("Update Successful");
                infoAlert.setContentText("Contact number has been updated");
                infoAlert.showAndWait();
            }
        } catch (InputValidationException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input error");
            errorAlert.setContentText("Please enter a valid UK telephone or mobile number");
            errorAlert.showAndWait();
        } catch (SQLException e) {
        } catch (Exception e) {
        }

    }

    /*
      Method is used when data is entered into the street address field and the update button is clicked
      Used to validate input and change the street address of a member on the database
                                                                                              */
    public void changeStreetAddress() {

        /*
        Method is used when data is entered into the last name field and the update button is clicked
         Used to change the validate input and change the last name of a member on the database
                                                 */
        try {

            if (!sAddressTxtField.getText().trim().isEmpty()) {

                //Get and set first name of member object that was created during the data build
                addressData.get(0).setStreetAddress(sAddressTxtField.getText());

                //Make connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
                //Create  java preparedstatement using a sql update query
                String SQL = "UPDATE addresses SET Street_address = ?  WHERE Member_ID = ?";
                PreparedStatement ps = connection.prepareStatement(SQL);

                ps.setString(1, addressData.get(0).getStreetAddress());
                ps.setInt(2, memberData.get(0).getMemberID());

                // call executeUpdate to execute sql update statement
                ps.executeUpdate();
                ps.close();

            }
        } catch (InputValidationException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input error");
            errorAlert.setContentText("Please enter a street address ");
            errorAlert.showAndWait();
        } catch (SQLException e) {
        } catch (Exception e) {
        }

    }



}















