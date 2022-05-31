package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static Scene changeScene(String fxml) throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        Parent pane = FXMLLoader.load(classLoader.getResource(fxml));

        Stage stage = new Stage();
        Scene newScene = new Scene(pane);
        newScene.getStylesheets().add("Style.css");
        stage.setScene(newScene);
        stage.show();

        return newScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
