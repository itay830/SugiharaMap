package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Map extends ZoomableScrollPane {
    protected void initViews() {
        try {
            for (var method : ViewBuilder.initialize(getClass())) {
                method.invoke(this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StackPane mapRoot;
    private Pane pCountries;


    public Map() {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("map.fxml"));
        StackPane root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super(new Group(root));
        mapRoot = root;
        initViews();
    }

    @NodeInitializer
    private void initPCountries() {
        pCountries = (Pane) mapRoot.lookup("#pCountries");
        var children = pCountries.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Circle child = (Circle) children.get(i);
            Landmark landmark = new Landmark();
            landmark.setCountry(child.getAccessibleText());
            children.add(i, landmark);
            landmark.setPosition(child.getLayoutX(), child.getLayoutY());
            children.remove(child);
        }
    }


}
