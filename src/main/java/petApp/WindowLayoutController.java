package petApp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class WindowLayoutController implements Initializable {
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, String> nameTableColumn;
    @FXML
    private TableColumn<Pet, String> ownerTableColumn;
    @FXML
    private TableColumn<Pet, Double> ageTableColumn;
    @FXML
    private TableColumn<Pet, Integer> kindTableColumn;

    @FXML
    private GridPane infoPane;
    @FXML
    private ImageView infoPaneImage;
    @FXML
    private Text infoPaneKindText;
    @FXML
    private Text infoPaneNameText;
    @FXML
    private Text infoPaneOwnerText;
    @FXML
    private Text infoPaneYearText;
    @FXML
    private Text infoPaneMonthText;

    @FXML
    private MenuItem editMenuButton;
    @FXML
    private MenuItem deleteMenuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Gson parser = new Gson();
        Image defaultImage = null;
        try {
            String jsonData = Files.readString(Path.of(getClass().getResource("/Pets.json").toURI()));
            JsonArray jsonPets = parser.fromJson(jsonData, JsonArray.class);
            ObservableList<Pet> pets = FXCollections.observableArrayList();
            for (JsonElement jsonPet: jsonPets)
                pets.add(new Pet(jsonPet));
            petTable.setItems(pets);
            defaultImage = new Image(getClass().getResource("/img.jpg").toString());
            infoPaneImage.setImage(defaultImage);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR).showAndWait();
        }


        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kindTableColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));
        ownerTableColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        ageTableColumn.setCellValueFactory(new Callback<>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Pet, Double> param) {
                return new SimpleObjectProperty<Double>(param.getValue().getAge());
            }
        });

        infoPane.setVisible(false);

        petTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                if (observable.getValue() != null) {
                    infoPane.setVisible(true);
                    setInfoPane(observable.getValue());
                }
                else {
                    infoPane.setVisible(false);
                }
        });
    }

    public void createPet(ActionEvent actionEvent) throws Exception{
        Pet editablePet = new Pet("", "", "", 0, 0);
        startPetInputWindow(editablePet);
        if (editablePet.isCorrect()) {
            petTable.getItems().add(editablePet);
        }
    }

    public void editPet(ActionEvent actionEvent) throws IOException {
        Pet editablePet = petTable.getSelectionModel().getSelectedItem();
        startPetInputWindow(editablePet);
        petTable.refresh();
        setInfoPane(editablePet);
    }

    private void startPetInputWindow(Pet editablePet) throws IOException {

        FXMLLoader inputWindowLoader = new FXMLLoader();
        inputWindowLoader.setLocation(Main.class.getResource("/PetInputWindowLayout.fxml"));

        Scene scene = new Scene(inputWindowLoader.load());
        PetInputWindowController controller = inputWindowLoader.getController();
        controller.setEditablePet(editablePet);

        Stage inputStage = new Stage();
        inputStage.setScene(scene);
        inputStage.initModality(Modality.APPLICATION_MODAL);

        inputStage.showAndWait();
    }

    public void deletePet(ActionEvent actionEvent) {
        SelectionModel selection = petTable.getSelectionModel();
        petTable.getItems().remove(selection.getSelectedItem());
    }

    private void setInfoPane(Pet pet) {
        infoPaneNameText.setText(pet.getName());
        infoPaneKindText.setText(pet.getKind());
        infoPaneOwnerText.setText(pet.getOwner());
        infoPaneYearText.setText(String.valueOf(pet.getYear()));
        infoPaneMonthText.setText(String.valueOf(pet.getMonth()));
    }
}
