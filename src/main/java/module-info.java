module com.example.sugiharamap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sugiharamap to javafx.fxml;
    exports com.example.sugiharamap;
    exports com.example.sugiharamap.main;
    opens com.example.sugiharamap.main to javafx.fxml;
}