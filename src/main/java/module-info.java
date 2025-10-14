module com.example.sugiharamap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.sugiharamap to javafx.fxml;
    exports com.example.sugiharamap;
    exports com.example.sugiharamap.pages.main;
    opens com.example.sugiharamap.customNodes to javafx.fxml;
    opens com.example.sugiharamap.pages.main to javafx.fxml;
    exports com.example.sugiharamap.customNodes;
}