package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.customNodes.Map;
import com.example.sugiharamap.customNodes.ZoomableScrollPane;
import com.example.sugiharamap.utils.math.MathUtil;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

import java.io.IOException;

public class MainViewBuilderBeta extends ViewBuilder {
    private Parent parent;
    private BorderPane root;
    private Map map;

    public Region build() {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("mainBeta.fxml"));
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

    @NodeInitializer(order = 0)
    private void initMap()
    {
        map = new Map();
        root.setCenter(map);
    }
}
