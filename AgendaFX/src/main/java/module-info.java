module com.ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.ejemplo to javafx.fxml;
    exports com.ejemplo;
}
