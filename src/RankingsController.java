import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RankingsController implements Initializable {


    @FXML private Button bckBtn;
    @FXML private WebView webView;

String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Open ranking page from weightlifting scotland website

        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://weightliftingscotland.com/Scottish%20Rankings.html");


        bckBtn.setOnAction(event ->{

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


}

