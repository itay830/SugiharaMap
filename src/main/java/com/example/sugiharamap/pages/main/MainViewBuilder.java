package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.customNodes.ZoomableScrollPane;
import com.example.sugiharamap.utils.math.MathUtil;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;

import java.io.IOException;

public class MainViewBuilder extends ViewBuilder {
    private static final double ZOOM_FACTOR = 1.1;
    private final Scale scaleTransform = new Scale(1.0, 1.0);

    private Parent parent;
    @FXML
    private ImageView map;

    @FXML
    private StackPane groupWrapper;

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

    public Region build() {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("Main.fxml"));
        Region root;
        loader.setController(this);
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }

    protected void initViews() {
        try {
            for (var method : initialize(getClass())) {
                method.invoke(this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void initScroll() {
        group.getTransforms().add(scaleTransform);
        scroll.addEventFilter(ScrollEvent.SCROLL, event -> {
            event.consume();
            System.out.println(group.boundsInParentProperty().get().getMaxX() - group.boundsInParentProperty().get().getMinX());
//            System.out.println(MathUtil.scaleRange(0, scroll.getWidth(), group.boundsInParentProperty().get().getMinX(), group.boundsInParentProperty().get().getMaxX(), event.getX()));
            scaleTransform.setPivotX(
                    MathUtil.scaleRange(0, scroll.getWidth(), group.boundsInParentProperty().get().getMinX(), group.boundsInParentProperty().get().getMaxX(), event.getX())
            );
            scaleTransform.setPivotY(
                    MathUtil.scaleRange(0, scroll.getHeight(), group.boundsInParentProperty().get().getMinY(), group.boundsInParentProperty().get().getMaxY(), event.getY())
            );

            //            double scale = group.getScaleX();
            double scale = scaleTransform.getX();
            if (event.getDeltaY() > 0) {
                scale *= ZOOM_FACTOR;
            } else {
                scale /= ZOOM_FACTOR;
            }
            scale = Math.clamp(scale, 0.5, 3);
//            group.setScaleX(scale);
//            group.setScaleY(scale);
            scaleTransform.setX(scale);
            scaleTransform.setY(scale);
        });

        group.boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
//            groupWrapper.setMinWidth(newBounds.getWidth());
//            groupWrapper.setMinHeight(newBounds.getHeight());
        });
    }

    @NodeInitializer
    private void initRoutes() {
        RouteService.init(content);
//        content.getChildren().add(new ZoomableScrollPane());
        Pane BertieFrankel = RouteService.getRoutes("Poland", "Lithuania", "Vladivostok", "Japan", "Indonesia", "New Zealand", "Israel");
        content.getChildren().add(1, BertieFrankel);
    }
}
