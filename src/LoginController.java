import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This class is the controller class
 * for the Login fxml view
 *
 *
 *
 **/


public class LoginController implements Initializable {


    @FXML
    private TextField unameTxtField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink registerLink, guestLink;

    public static Member newLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


         /*
        Event listener / controller for the Login button
       */
        loginBtn.setOnAction(event -> {

                    try {

                        if (unameTxtField.getText().trim().isEmpty() || passField.getText().trim().isEmpty()) {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Error");
                            errorAlert.setContentText("Please ensure both the username and password are entered");
                            errorAlert.showAndWait();

                        }  else if (loginCheck(unameTxtField.getText(), passField.getText())) {


                            if(unameTxtField.getText().equals("admin"))

                            //If admin load admin screen
                            {

                                Parent regParent = FXMLLoader.load(getClass().getResource("FXML FILES/AdminDash.fxml"));
                                Scene regScene = new Scene(regParent);
                                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                appStage.setScene(regScene);
                                appStage.show();

                           //else load guest screen
                            } else {

                                newLogin = new Member(unameTxtField.getText());
                                System.out.println(newLogin.getUsername());

                                Parent regParent = FXMLLoader.load(getClass().getResource("FXML FILES/Dashboard.fxml"));
                                Scene regScene = new Scene(regParent);
                                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                appStage.setScene(regScene);
                                appStage.show();
                            }
                        } else {

                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Error");
                            errorAlert.setContentText("Input is not valid");
                            errorAlert.showAndWait();
                            unameTxtField.clear();
                            passField.clear();
                        }
                    } catch (InputValidationException e) {
                    } catch (IOException e) {
                    }
                }
        );

         /*
        Event listener / controller for the Register button
       */
        registerLink.setOnAction(event -> {

            try {

                Parent regParent = FXMLLoader.load(getClass().getResource("FXML FILES/Registration.fxml"));
                Scene regScene = new Scene(regParent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                app_stage.setScene(regScene);
                app_stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        );


        /*
        Event listener / controller for the Guest button - A guest can access the member dashboard  but with limited funtionality
       */
        guestLink.setOnAction(event -> {

            try {

                Parent regParent = FXMLLoader.load(getClass().getResource("FXML FILES/Dashboard.fxml"));
                Scene regScene = new Scene(regParent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                app_stage.setScene(regScene);
                app_stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }



    /**
     * Method validates user login credentials and gives out a boolean output if they are valid or not
     *
     * @param username
     **/
//
    public boolean loginCheck(String username, String password) throws InputValidationException {

        boolean isValid = false;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Accounts WHERE username = " + "'" + username + "'"
                    + " AND password = " + "'" + password + "'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getString("Username") != null && rs.getString("Password") != null) {

                    isValid = true;

                }
            }
             connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;

    }


}

