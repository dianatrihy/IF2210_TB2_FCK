module org.example.if2210_tb2_fck {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.io;

    opens org.example.if2210_tb2_fck to javafx.fxml;
    exports org.example.if2210_tb2_fck;

    opens org.example.if2210_tb2_fck.model to javafx.fxml;
    exports org.example.if2210_tb2_fck.model;

    opens org.example.if2210_tb2_fck.model.Item to javafx.fxml;
    exports org.example.if2210_tb2_fck.model.Item;

    opens org.example.if2210_tb2_fck.controller to javafx.fxml;
    exports org.example.if2210_tb2_fck.controller;

    opens org.example.if2210_tb2_fck.command to javafx.fxml;
    exports org.example.if2210_tb2_fck.command;
}