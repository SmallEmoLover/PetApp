package petApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader windowLoader = new FXMLLoader();
       windowLoader.setLocation(Main.class.getResource("/WindowLayout.fxml"));
       primaryStage.setScene(new Scene(windowLoader.load()));

       primaryStage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}
