module com.utc2.giangviendemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    exports com.utc2.giangviendemo.controller;
    opens com.utc2.giangviendemo.controller to javafx.fxml;
    exports com.utc2.giangviendemo;
    opens com.utc2.giangviendemo to javafx.fxml;
}