package com.example.sugiharamap.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.utils.BoundLine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;

import java.io.IOException;

public class MainViewBuilder {
    private static final double ZOOM_FACTOR = 1.1;

    private Parent parent;

    @FXML
    private Group group;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Pane field;

    @FXML
    ToggleButton btn1;

    @FXML
    ToggleButton btn2;

    public Parent build() {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("Main.fxml"));
        Parent root;
        loader.setController(this);
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }

    public void initViews() {
        initBtn();
        initGroup();
        initScroll();
    }

    private void initBtn() {
        btn1.setOnMouseEntered(mouseEvent -> System.out.println("HOVER"));
        btn1.setOnMouseExited(mouseEvent -> System.out.println("EXIT"));
        btn1.setOnMouseClicked(mouseEvent -> {
            System.out.println("Clicked");
            System.out.println(btn1.boundsInParentProperty().get().getWidth() / 2);
        });

        Line line = new BoundLine(btn1, btn2, true);
        field.getChildren().add(0, line);
    }

    private void initScroll() {
        scroll.setOnScroll(event -> {
            event.consume();
            double deltaY = event.getDeltaY();
            if (deltaY == 0) {
                return;
            }
            double scaleFactor = (deltaY > 0) ? ZOOM_FACTOR : 1 / ZOOM_FACTOR;
            group.setScaleX(group.getScaleX() * scaleFactor);
            group.setScaleY(group.getScaleY() * scaleFactor);
        });
    }

    private void initGroup() {
        Scale scale = new Scale(1, 1);
        group.getTransforms().add(scale);
    }
}
