package org.example.if2210_tb2_fck;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class YourController {

    @FXML
    private AnchorPane anchorPane; // Assuming this is your AnchorPane in FXML
    private final double padding = 10;
    private int row;            // Number of rows
    private int col;            // Number of columns

    private final double originalWidth = 92;
    private final double originalHeight = 105;
    private final double aspectRatio = originalWidth / originalHeight;

    // Your initialization method or wherever you set row and col values
    public void initialize() {
        // Example values, replace with your logic to set row and col
        row = 7;
        col = 6;
        generateCustomPanes();
    }

    private Color generateColor(int index) {
        // Simple color generation logic: cycle through a hue value
        double hue = (index * 137.50776405) % 360; // Use a large prime number to get varied hues
        return Color.hsb(hue, 0.8, 0.9); // Saturation and brightness values are fixed
    }
    int paneCount = 1;
    private void generateCustomPanes() {
        double availableWidth = 629 - (col + 1) * padding;
        double availableHeight = 485 - (row + 1) * padding;
        double horizontalPadding = (availableWidth - col * 629) / (col + 1);
        double verticalPadding = (availableHeight - row * 485) / (row + 1);
        double paneWidth = availableWidth / col;
        double paneHeight = availableHeight / row;

        if (paneWidth / originalWidth < paneHeight / originalHeight) {
            paneHeight = paneWidth / aspectRatio;
        } else {
            paneWidth = paneHeight * aspectRatio;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(paneWidth, paneHeight);

                // Set position of the pane based on the row and column
                pane.setLayoutX(padding + j * (paneWidth + padding));
                pane.setLayoutY(padding + i * (paneHeight + padding));
                Color color = generateColor(paneCount);
                String colorStyle = String.format("-fx-background-color: #%02X%02X%02X;", 
                                                  (int) (color.getRed() * 255), 
                                                  (int) (color.getGreen() * 255), 
                                                  (int) (color.getBlue() * 255));
                pane.setStyle(colorStyle);
                pane.setStyle("-fx-border-color: white;");

                pane.setId("Pane" + i + j);
                System.out.println("Pane" + i + j);
                paneCount++;
                // Add the pane to your AnchorPane
                anchorPane.getChildren().add(pane);
            }
        }
    }
}
