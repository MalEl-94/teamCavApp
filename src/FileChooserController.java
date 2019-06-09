import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import javafx.scene.control.ListView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;



/**
 * This class is for the file chooser
 * Allowing admin to choose a file to upload
 *
 *
 * @author Ahad Malik
 **/

public class FileChooserController {


    @FXML
    Button singleFileBtn, multiFileBtn;
    @FXML
    ListView listView;


    public void setSingleFileBtnAction(ActionEvent actionEvent) {



        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Excel Files", "*.xl xs"));

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if (selectedFiles != null) {

            for (int i = 0; i < selectedFiles.size(); i++) {
                listView.getItems().add(selectedFiles.get(i).getAbsoluteFile());
            }

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("File is not valid");
            errorAlert.setContentText("Please ensure file chosen is of a valid type");
            errorAlert.showAndWait();

        }


    }
}
