module calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens calculator to javafx.fxml;

    exports calculator;
}
