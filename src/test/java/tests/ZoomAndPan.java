package tests;

import com.example.sugiharamap.customNodes.ZoomableScrollPane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ZoomAndPan extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new ZoomableScrollPane(new Group(new Circle(10))));
        stage.setScene(scene);
        stage.show();
    }
}
