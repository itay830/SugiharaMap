package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.customNodes.WorldMap;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class MainViewBuilder extends ViewBuilder {
    private final MainModel model;
    private Parent parent;
    private BorderPane root;
    private WorldMap worldMap;

    public MainViewBuilder(MainModel model) {
        this.model = model;
    }

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
        worldMap = new WorldMap();
        root.setCenter(worldMap);
//        worldMap.setRoutesByCountries("Poland", "Lithuania", "Vladivostok", "Japan", "Indonesia", "New Zealand", "Israel");
        worldMap.setRoutesByRouteStory(model.routeStories.getFirst());
    }


}
