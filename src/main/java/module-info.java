module com.example.soulspire {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.soulspire to javafx.fxml;
    exports com.example.soulspire;
}