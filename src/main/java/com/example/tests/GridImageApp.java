package com.example.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridImageApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        final int rows = 50;
        final int columns = 50;
        String imagePath = getClass().getResource("/Pics/DarkCastle_19_16x16.png").toExternalForm();
        Image image = new Image(imagePath);

        GridPane gridPane = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                gridPane.add(imageView, col, row);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Grid of Images");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.widthProperty().addListener((observable, oldValue, newValue) -> adjustImageSize(gridPane, newValue.doubleValue() / columns));
        scene.heightProperty().addListener((observable, oldValue, newValue) -> adjustImageSize(gridPane, newValue.doubleValue() / rows));
    }

    private void adjustImageSize(GridPane gridPane, double newSize) {
        gridPane.getChildren().forEach(node -> {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setFitWidth(newSize);
                imageView.setFitHeight(newSize);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
