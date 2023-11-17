package thedrake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TheDrakeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene MainMenu = new Scene(FXMLLoader.load(Objects.requireNonNull(TheDrakeApplication.class.getResource("intro-page.fxml"))), 800, 500);
        stage.setTitle("The Drake!");
        stage.setScene(MainMenu);
        stage.show();
    }
}