package com.example.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tests/hello-view.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        HelloController controller = loader.getController();
        scene.setOnKeyPressed(controller::handleKeyPress);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.fullScreenProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                controller.setGridSize(100, 100);
            } else {
                controller.setGridSize(50, 50);
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
