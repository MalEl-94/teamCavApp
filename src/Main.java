import com.dropbox.core.DbxWebAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;




public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws DbxWebAuth.Exception {

       try{
        Parent root = FXMLLoader.load(getClass().getResource("FXML FILES/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }  catch (IOException ioe) {
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
           errorAlert.setHeaderText("Error");
           errorAlert.setContentText("Login screen could not be loaded");
           errorAlert.showAndWait();
    }


}
    public static void main(String[] args) {
        launch(args);
    }

}