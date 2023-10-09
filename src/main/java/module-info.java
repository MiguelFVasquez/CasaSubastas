module com.example.casasubastas {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;

    requires org.mapstruct;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.pr3.subastas.application to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.pr3.subastas.model to javafx.base;
    opens co.edu.uniquindio.pr3.subastas.viewControllers to javafx.fxml;
    exports co.edu.uniquindio.pr3.subastas.mapping.mappers;
    exports co.edu.uniquindio.pr3.subastas.model;
}