package org.example.if2210_tb2_fck.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TimerController {
    @FXML 
    private Text sisaWaktu;

    @FXML
    private Pane timerPane;

    public void initialize(){}

    public void setTimer(int i){
        sisaWaktu.setText(i+"");
    }
}
