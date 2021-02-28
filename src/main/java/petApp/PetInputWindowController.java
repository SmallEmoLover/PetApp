package petApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.PushbackInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class PetInputWindowController implements Initializable {

    private Pet editablePet;

    @FXML
    private ImageView petImage;
    @FXML
    private TextField petName;
    @FXML
    private TextField petKind;
    @FXML
    private TextField petOwner;
    @FXML
    private Spinner petYear;
    @FXML
    private Spinner petMonth;

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
        petName.setText(editablePet.getName());
        petOwner.setText(editablePet.getOwner());
        petKind.setText(editablePet.getKind());
        petYear.getValueFactory().setValue(editablePet.getYear());
        petMonth.getValueFactory().setValue(editablePet.getMonth());
    }
}
