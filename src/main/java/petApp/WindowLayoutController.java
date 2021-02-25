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
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URISyntaxException;
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
    private MenuItem editMenuButton;
    @FXML
    private MenuItem deleteMenuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kindTableColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));
        ownerTableColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        ageTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pet, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Pet, Double> param) {
                return new SimpleObjectProperty<Double>(param.getValue().getAge());
            }
        });

        Gson parser = new Gson();
        try {
            String jsonData = Files.readString(Path.of(getClass().getResource("/Pets.json").toURI()));
            JsonArray jsonPets = parser.fromJson(jsonData, JsonArray.class);
            ObservableList<Pet> pets = FXCollections.observableArrayList();
            for (JsonElement jsonPet: jsonPets)
                pets.add(new Pet(jsonPet));
            petTable.setItems(pets);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void createPet(ActionEvent actionEvent) {
    }

    public void editPet(ActionEvent actionEvent) {
    }

    public void deletePet(ActionEvent actionEvent) {
    }
}
