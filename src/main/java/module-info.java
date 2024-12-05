module com.example.tests {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tests to javafx.fxml;
    exports com.example.tests;
}