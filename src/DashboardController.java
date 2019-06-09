
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This class is the controller class
 * for the Dashboard fxml view
 *
 *
 *
 **/


public class DashboardController implements Initializable {


    @FXML
    Button trainingBtn, eventBtn, wlsBtn, detailsBtn, logoutBtn;

    @FXML
    Pane secPane, dashPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        secPane.getChildren().clear();
        try {
            secPane.getChildren().add(FXMLLoader.load(getClass().getResource("FXML FILES/LifterOfTheMonth.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }


      /*

      Event listener / controller for Training button

    */
        trainingBtn.setOnAction(event -> {


        });


       /*

      Event listener / controller for the Event calendar button

    */
        eventBtn.setOnAction(event -> {

            secPane.getChildren().clear();

            try {
                secPane.getChildren().add(FXMLLoader.load(getClass().getResource("FXML FILES/EventCalendar.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


         /*
      Event listener / controller for the Rankings button

    */
        wlsBtn.setOnAction(event -> {

            secPane.getChildren().clear();

            try {
                secPane.getChildren().add(FXMLLoader.load(getClass().getResource("FXML FILES/Rankings.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        /*

      Event listener / controller for the Edit details button

    */
        detailsBtn.setOnAction(event -> {

            secPane.getChildren().clear();

            try {
                secPane.getChildren().add(FXMLLoader.load(getClass().getResource("FXML FILES/EditDetails.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

         /*
      Event listener / controller for the logout button

    */
        logoutBtn.setOnAction(event -> {

            try {

                //Confirm logout alert is displayed to user
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setHeaderText("Confirm");
                confirmAlert.setContentText("Are you sure you want to logout?");

                //If OKAY button is pressed
                confirmAlert.showAndWait().filter(ButtonType.OK::equals).ifPresent((b -> {

                    try {
                        Parent loginParent = null;
                        loginParent = FXMLLoader.load(getClass().getResource("FXML FILES/Login.fxml"));
                        Scene loginScene = new Scene(loginParent);
                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(loginScene);
                        app_stage.show();

                        LoginController.newLogin = null;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });






    }

}
