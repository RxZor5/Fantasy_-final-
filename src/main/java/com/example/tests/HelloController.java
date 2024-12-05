package com.example.tests;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ImageView spriteImageView;

    @FXML
    private GridPane gridPane;

    private int rows = 36; // Für Full HD
    private int columns = 64; // Für Full HD

    private double xPos = 50; // Initiale X-Position des Sprites
    private double yPos = 50; // Initiale Y-Position des Sprites

    @FXML
    public void initialize() {
        String imagePath = getClass().getResource("/Pics/DarkCastle_19_16x16.png").toExternalForm();
        Image image = new Image(imagePath);

        gridPane.widthProperty().addListener((observable, oldValue, newValue) -> fillGridPane(image));
        gridPane.heightProperty().addListener((observable, oldValue, newValue) -> fillGridPane(image));

        fillGridPane(image);

        // Sprite Animation
        final int spriteColumns = 4;
        final int spriteCount = 4;
        final int offsetX = 0;
        final int offsetY = 0;
        final int width = 32;
        final int height = 32;
        final String spritePath = "/Pics/Idle-Sheet.png";

        Animation orc = new SpriteAnimation(
                spriteImageView,
                Duration.millis(500),
                spriteCount, spriteColumns,
                offsetX, offsetY,
                width, height,
                spritePath
        );
        orc.setCycleCount(Animation.INDEFINITE);
        orc.play();

        // Setze die Anfangsposition des Sprites
        spriteImageView.setLayoutX(xPos);
        spriteImageView.setLayoutY(yPos);
    }

    public void setGridSize(int newRows, int newColumns) {
        this.rows = newRows;
        this.columns = newColumns;
        String imagePath = getClass().getResource("/Pics/DarkCastle_19_16x16.png").toExternalForm();
        Image image = new Image(imagePath);
        fillGridPane(image);
    }

    private void fillGridPane(Image image) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        for (int i = 0; i < columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / columns);
            gridPane.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            gridPane.getRowConstraints().add(rowConst);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(false);  // Sicherstellen, dass das Bild die Zelle vollständig ausfüllt
                imageView.setFitWidth(gridPane.getWidth() / columns);
                imageView.setFitHeight(gridPane.getHeight() / rows);
                gridPane.add(imageView, col, row);
            }
        }
    }

    @FXML
    void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                yPos -= 10; // Nach oben bewegen
                break;
            case S:
                yPos += 10; // Nach unten bewegen
                break;
            case A:
                xPos -= 10; // Nach links bewegen
                break;
            case D:
                xPos += 10; // Nach rechts bewegen
                break;
            default:
                break;
        }
        spriteImageView.setLayoutX(xPos);
        spriteImageView.setLayoutY(yPos);
    }
}
