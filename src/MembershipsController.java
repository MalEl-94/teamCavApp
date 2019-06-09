import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This class is the controller class
 * for the Memberships fxml view
 *
 *
 *
 **/

public class MembershipsController implements Initializable {


    @FXML
    private TableView memberTable;
    @FXML
    private TableColumn memberIDCol, firstNameCol, lastNameCol, dateOfBirthCol, emailCol;
    @FXML
    private Button delMemberBtn,bckBtn;
    @FXML
    private TextField delMemberTxtField;

    private ObservableList<Member> data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buildData();
        memberTable.setItems(null);
        memberTable.setItems(data);


             /*
      Event listener / controller for the delete member button

    */
        delMemberBtn.setOnAction(event -> {



            if(!delMemberTxtField.getText().trim().isEmpty()) {


                try {

                    MySQL data = MySQL.getInstance();
                    data.deleteMember(Integer.parseInt(delMemberTxtField.getText()));


                } catch (Exception e) {

                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Error");
                    errorAlert.setContentText("Please enter a Member ID number");
                    errorAlert.showAndWait();
                }
            }

        });

         /*
      Event listener / controller for the back button

    */
        bckBtn.setOnAction(event -> {

            try {

                Parent dashParent = FXMLLoader.load(getClass().getResource("FXML FILES/AdminDash.fxml"));
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
     * Method retrives a list of All members from tables and shows it in a tableview
     *
     **/
    public void buildData() {

        data = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
            //Query to select all members
            String SQL = "SELECT * from Members";
            //ResultSet
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            //
            while (rs.next()) {

                data.add(new Member(rs.getInt("Member_ID"), rs.getString("First_name"), rs.getString("Last_name"),
                        rs.getString("Date_of_birth"), rs.getString("Email"), rs.getString("WLS_number")));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InputValidationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set cell value factory for tableview

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        memberIDCol.setCellValueFactory((new PropertyValueFactory<>("memberID")));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));


    }


}







