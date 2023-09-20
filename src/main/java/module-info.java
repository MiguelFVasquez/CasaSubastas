module com.example.casasubastas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.casasubastas to javafx.fxml;
    exports com.example.casasubastas;
}