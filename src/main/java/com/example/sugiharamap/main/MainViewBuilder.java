package com.example.sugiharamap.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.utils.BoundLine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;

import java.io.IOException;

public class MainViewBuilder {
    private static final double ZOOM_FACTOR = 1.1;

    private Parent parent;
    @FXML
    private ImageView map;

    @FXML
    private StackPane wrapper;
    @FXML
    private Group group;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Pane content;

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
        initScroll();
    }

    private void initScroll() {
        scroll.addEventFilter(ScrollEvent.SCROLL, event -> {
            event.consume();
            double scale = group.getScaleX(); // ScaleX = ScaleY by default
            if (event.getDeltaY() > 0) {
                scale *= ZOOM_FACTOR;
            } else {
                scale /= ZOOM_FACTOR;
            }
            scale = Math.clamp(scale, 1, 3);

            group.setScaleX(scale);
            group.setScaleY(scale);
        });
        group.boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            wrapper.setMinWidth(newBounds.getWidth());
            wrapper.setMinHeight(newBounds.getHeight());
        });
    }
}
