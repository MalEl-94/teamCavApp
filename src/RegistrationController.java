import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import  javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class is the controller class
 * for the Registration fxml view
 *
 *
 *
 **/

public class RegistrationController implements Initializable {


@FXML
   private TextField fNameTxtField, lNameTxtField, dobTxtField,contactNumbTxtField,sAddressTxtField,cityTxtField,pCodeTxtField,
        wlsNumbTxtField,uNameTxtField,emailTxtField;
@FXML
   private PasswordField passwordField,confPasswordField;
@FXML
   private Button submitBtn,backBtn;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


   /*
      Event listener / controller for the submit button
      Validates user data input and throws exceptions if input is incorrect
      Alert messages are used to notify the user of any errors

    */

    submitBtn.setOnAction(event -> {


        Member newMember= new Member();
        Address newAddress = new Address();
        boolean isInputValidationerror = false;
        boolean isFilled = true;



//First name field validation
        if (fNameTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your first name");
            errorAlert.showAndWait();
            isFilled = false;
        } else
                    try {
                        newMember.setFirstName(fNameTxtField.getText());
                    } catch (InputValidationException e) {
                        isInputValidationerror = true;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("First name must be  2 -20 characters long and cannot contain numbers or special characters");
                        errorAlert.showAndWait();
                    }

//Last name field validation
        if (lNameTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your last name");
            errorAlert.showAndWait();
            isFilled = false;
        } else
                    try {
                        newMember.setLastName(lNameTxtField.getText());
                    } catch (InputValidationException e) {
                        isInputValidationerror = true;

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("Last name must be  2 -20 characters long and cannot contain numbers or special characters");
                        errorAlert.showAndWait();
                    }

//Date of birth field validation
        if (dobTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your date of birth");
            errorAlert.showAndWait();
            isFilled = false;
        } else
                    try {
                        newMember.setDob(dobTxtField.getText());
                    } catch (Exception e) {
                        isInputValidationerror = true;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("Please ensure the date of birth in the correct format (dd/mm/yyyy)");
                        errorAlert.showAndWait();
                    }

//Contact number field validation
        if (contactNumbTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your primary contact number");
            errorAlert.showAndWait();
            isFilled = false;
        } else
                    try {
                        newMember.setContactNumber(contactNumbTxtField.getText());
                    } catch (InputValidationException e) {
                        isInputValidationerror = true;

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("Please enter a valid UK telephone or mobile number without spaces");
                        errorAlert.showAndWait();
                    }


//Street address field validation
        if (sAddressTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your street address");
            errorAlert.showAndWait();
            isFilled=false;
        } else
                    try {
                        newAddress.setStreetAddress(sAddressTxtField.getText());
                    } catch (InputValidationException e) {
                        isInputValidationerror = true;

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("Please enter a street address");
                        errorAlert.showAndWait();
                    }

//City field validation
        if (cityTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter the name of your current city");
            errorAlert.showAndWait();
            isFilled=false;
        } else
                    try {
                        newAddress.setCity(cityTxtField.getText());
                    }
                    catch (InputValidationException e) {
                        isInputValidationerror = true;

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Input not valid");
                        errorAlert.setContentText("Please enter a valid city name");
                        errorAlert.showAndWait();
                    }

//Postcode field validation
        if (pCodeTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your current postcode");
            errorAlert.showAndWait();
            isFilled=false;
        } else
               try {
                   newAddress.setPostcode(pCodeTxtField.getText());
                 }

                 catch (InputValidationException e) {
                 isInputValidationerror = true;
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                 errorAlert.setHeaderText("Postcode not valid");
                 errorAlert.setContentText("Please enter a valid UK postcode");
                 errorAlert.showAndWait();
                 e.printStackTrace();
            }


//Email field validation
        if (emailTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter your email address");
            errorAlert.showAndWait();
            isFilled=false;
        } else
                    try {
                        newMember.setEmail(emailTxtField.getText());
                    } catch (InputValidationException e) {
                        isInputValidationerror = true;

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Email not valid");
                        errorAlert.setContentText("Please enter a valid email address");
                        errorAlert.showAndWait();
                    }

//WLS number field validation (Optional)
        try {
            if (!wlsNumbTxtField.getText().trim().isEmpty()) {
                newMember.setWlsNumber(wlsNumbTxtField.getText());
            }
                } catch (InputValidationException e) {
                    isInputValidationerror = true;
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("WLS number not valid");
                    errorAlert.setContentText("WLS number can only contain numbers between 1 - 9999");
                    errorAlert.showAndWait();
                }

//Username validation
        if (uNameTxtField.getText().trim().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter a username");
            errorAlert.showAndWait();
            isFilled=false;

        } else try {
            if(!MySQL.checkUsernameExists(uNameTxtField.getText())) {

                try {
                    newMember.setUsername(uNameTxtField.getText());
                } catch (InputValidationException e) {
                    isInputValidationerror = true;
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Input not valid");
                    errorAlert.setContentText("Username must be 6-20 characters long and must start with a letter");
                    errorAlert.showAndWait();
                }
            }   else {
                      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                      errorAlert.setHeaderText("Oops!");
                      errorAlert.setContentText("Username already exists");
                      errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//Password validation

        if (passwordField.getText().trim().isEmpty()&&confPasswordField.getText().trim().isEmpty()) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Input Detected");
            errorAlert.setContentText("Please enter an appropriate password");
            errorAlert.showAndWait();
            isFilled=false;

          } else if (passwordField.getText().equals(confPasswordField.getText())){

            try {
                  newMember.setPassword(passwordField.getText());

               }catch (InputValidationException e) {
                isInputValidationerror = true;
                e.printStackTrace();

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Password not valid");
                errorAlert.setContentText("Password must be at least 8 characters in length. It must contain:\n" +
                                                                  "a minimum of 1 lower case letter [a-z] and\n" +
                                                                  "a minimum of 1 upper case letter [A-Z] and\n" +
                                                                       "a minimum of 1 numeric character [0-9] ");
                errorAlert.showAndWait();
            }
               } else {
                       isInputValidationerror=true;
                       Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                       errorAlert.setHeaderText("Passwords don't match!");
                       errorAlert.setContentText("Please make sure passwords match ");
                       errorAlert.showAndWait();
        }


         /*

         Pass on validated member details to save member method so they can be saved on to the database
         Only if their are now input validation errors and that all mandatory forms have been filled

         */

        if (!isInputValidationerror && isFilled) {

                try {

                    MySQL data = MySQL.getInstance();
                    data.saveMember(newMember, newAddress);
                    LoginController.newLogin.setMemberID(newMember.getMemberID());

                    //Notify user that they have now been succesfully registered onto the database
                    Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
                    confirmAlert.setHeaderText("You are now registered!");
                    confirmAlert.setContentText("Click OK to be directed to the member dashboard");
                    confirmAlert.showAndWait();


                    //New registered member is redirected to the dashboard and the new login user static variable is assigned the new members ID
                    //If OK is pressed on Information screen
                    confirmAlert.showAndWait().filter(ButtonType.OK::equals).ifPresent((b -> {

                        try {
                            Parent dashParent = null;
                            dashParent = FXMLLoader.load(getClass().getResource("FXML FILES/Dashboard.fxml"));
                            Scene dashScene = new Scene(dashParent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                            app_stage.setScene(dashScene);
                            app_stage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }));
                }catch (SQLException e){

                    //If an exception is thrown - notify user that they could not be added to the database
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Error");
                    errorAlert.setContentText("Member could not be added to the database");
                    errorAlert.showAndWait();
                    e.printStackTrace();

                } catch (Exception e) {
                }}

                else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Form not complete");
                errorAlert.setContentText("Please complete the form");
                errorAlert.showAndWait();
        }

});


   /*
      Event listener / controller for the Back button
      Returns user back to the login screen

    */
    backBtn.setOnAction(event ->{

      try {

          Parent loginParent = FXMLLoader.load(getClass().getResource("FXML FILES/Login.fxml"));
          Scene regScene = new Scene(loginParent);
          Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

          app_stage.setScene(regScene);
          app_stage.show();

            } catch (IOException e) {
              e.printStackTrace();
      }

    });


}


}





