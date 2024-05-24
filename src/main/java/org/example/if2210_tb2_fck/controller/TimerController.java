package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TimerController {
    @FXML 
    private Text sisaWaktu;

    @FXML
    private AnchorPane timerPane;

    public void initialize(){
        // Create and configure the Rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(207.0);
        rectangle.setHeight(52.0);
        rectangle.setFill(javafx.scene.paint.Color.rgb(250, 219, 62)); // Yellow color
        rectangle.setStroke(javafx.scene.paint.Color.WHITE);
        rectangle.setStrokeWidth(1.0);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);

        // Create and configure the "SISA WAKTU:" Text
        Text sisaWaktuText = new Text();
        sisaWaktuText.setLayoutX(14.0);
        sisaWaktuText.setLayoutY(21.0);
        sisaWaktuText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sisaWaktuText.setStrokeWidth(0.0);
        sisaWaktuText.setText("SISA WAKTU:");
        sisaWaktuText.setFont(Font.font("Bodoni MT", 17.0));

        // Create and configure the "seconds" Text
        Text secondsText = new Text();
        secondsText.setLayoutX(140.0);
        secondsText.setLayoutY(31.0);
        secondsText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        secondsText.setStrokeWidth(0.0);
        secondsText.setText("seconds");
        secondsText.setFont(Font.font("Bodoni MT", 16.0));

        // Create and configure the "30.7" Text (initial time)
        sisaWaktu = new Text();
        sisaWaktu.setLayoutX(98.0);
        sisaWaktu.setLayoutY(32.0);
        sisaWaktu.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sisaWaktu.setStrokeWidth(0.0);
        sisaWaktu.setText("30.7");
        sisaWaktu.setFont(Font.font("Bodoni MT", 22.0));

        // Create and configure the "WHOA ADA BERUANG MENYERANGGG!" Text
        Text bearAttackText = new Text();
        bearAttackText.setLayoutX(4.0);
        bearAttackText.setLayoutY(71.0);
        bearAttackText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        bearAttackText.setStrokeWidth(0.0);
        bearAttackText.setText("WHOA ADA BERUANG MENYERANGGG!");
        bearAttackText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        bearAttackText.setFont(Font.font("Bodoni Bd BT Bold", 20.0));

        // Add all elements to the timerPane
        timerPane.getChildren().addAll(rectangle, sisaWaktuText, secondsText, sisaWaktu, bearAttackText);
    }

    public void setTimer(int i){
        sisaWaktu.setText(i+"");
    }
}
