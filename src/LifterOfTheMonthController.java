import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class LifterOfTheMonthController implements Initializable{


 @FXML
    ImageView image;



    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }


    public static  String getMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }


}
