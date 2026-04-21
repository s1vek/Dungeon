module com.example.soulspire {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.example.soulspire to javafx.fxml;
    exports com.example.soulspire;
}