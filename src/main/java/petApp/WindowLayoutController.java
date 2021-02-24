package petApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowLayoutController implements Initializable {
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, String> nameTableColumn;
    @FXML
    private TableColumn<Pet, String> ownerTableColumn;
    @FXML
    private TableColumn<Pet, Integer> ageTableColumn;
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
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        //TODO: Заменить на JSON - файл
        ObservableList<Pet> pets = FXCollections.observableArrayList(new Pet("Cat", "Vasya", "Me", 12, 2)
                , new Pet("Human", "I", "You", 0, 5));

        petTable.setItems(pets);
    }

    public void createPet(ActionEvent actionEvent) {
    }

    public void editPet(ActionEvent actionEvent) {
    }

    public void deletePet(ActionEvent actionEvent) {
    }
}
