package petApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PushbackInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class PetInputWindowController implements Initializable {

    private Pet editablePet;

    @FXML
    private GridPane petPane;

    @FXML
    private ImageView petImage;
    @FXML
    private TextField petName;
    @FXML
    private TextField petKind;
    @FXML
    private TextField petOwner;
    @FXML
    private Spinner<Integer> petYear;
    @FXML
    private Spinner<Integer> petMonth;

    @FXML
    private void fileSelection(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image",
                "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(String.valueOf(file.toURI()));
            petImage.setImage(image);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image defaultImage = new Image(getClass().getResource("/img.jpg").toString());
        petImage.setImage(defaultImage);
    }

    public void setEditablePet(Pet editablePet) {
        this.editablePet = editablePet;
        setInfo(null);
    }

    @FXML
    private void editPet(ActionEvent actionEvent) {

        if (petName.getText().matches("[A-Za-z]+") &&
                petKind.getText().matches("[A-Za-z]+") &&
                petOwner.getText().matches("[A-Za-z]+")) {

            editablePet.setName(petName.getText());
            editablePet.setKind(petKind.getText());
            editablePet.setOwner(petOwner.getText());
            editablePet.setYear(petYear.getValue());
            editablePet.setMonth(petMonth.getValue());

            //FIXME
            close(null);
        }
        else {
            Text errorText = new Text("Incorrect input");
            petPane.add(errorText, 3, 3);
        }
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Stage stage = (Stage) petName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setInfo(ActionEvent actionEvent) {
        petName.setText(editablePet.getName());
        petOwner.setText(editablePet.getOwner());
        petKind.setText(editablePet.getKind());
        petYear.getValueFactory().setValue(editablePet.getYear());
        petMonth.getValueFactory().setValue(editablePet.getMonth());
    }
}
