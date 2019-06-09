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


/**
 * This class is the controller class
 * for the Event Calendar fxml view
 *
 *
 *
 **/

public class EventCalendarController implements Initializable {


    @FXML
    private Button bckBtn;
    @FXML
    private WebView webView;




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Open calendar of events pdf from weightlifting scotland website using google documents
        //Pdf is displayed in a webview

        WebEngine webEngine = webView.getEngine();
        String googleDocs = "http://drive.google.com/viewerng/viewer?embedded=true&url=";
        String pdfUrl = " http://weightliftingscotland.com/docs/Events%202018.pdf";
        webEngine.load(googleDocs+pdfUrl);



        bckBtn.setOnAction(event ->{


            try {

                Parent loginParent = FXMLLoader.load(getClass().getResource("FXML FILES/Dashboard.fxml"));
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










