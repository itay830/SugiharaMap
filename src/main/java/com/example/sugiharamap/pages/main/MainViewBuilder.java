package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.customNodes.WorldMap;
import com.example.sugiharamap.models.RouteStory;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainViewBuilder extends ViewBuilder {
    private final MainModel model;

    @FXML
    private StackPane mapContainer;
    @FXML
    private ChoiceBox<RouteStory> cbSurvivors;
    @FXML
    private Label lbName;
    @FXML
    private Text textDesc;


    private WorldMap worldMap;

    public Region build() {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("betterMain.fxml"));
        loader.setController(this);
        Region root;
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

    public MainViewBuilder(MainModel model) {
        this.model = model;
    }

    @NodeInitializer(order = 0)
    private void initMap() {
        worldMap = new WorldMap();
        mapContainer.getChildren().add(worldMap);
        worldMap.setRoutesByRouteStory(model.routeStories.getFirst());
    }

    @NodeInitializer
    private void initChSurvivors() {
        for (var route : model.routeStories) {
            cbSurvivors.getItems().add(route);
        }
        cbSurvivors.setOnAction((e) -> {
            var selectionItem = cbSurvivors.getSelectionModel().getSelectedItem();;
            int selectedIndex = cbSurvivors.getSelectionModel().getSelectedIndex();
            worldMap.setRoutesByRouteStory(model.routeStories.get(selectedIndex));
            model.selectedSurvivorName.set(selectionItem.getName());
            model.desc.set(selectionItem.getDesc());
        });
        cbSurvivors.getSelectionModel().selectFirst();
    }

    @NodeInitializer
    private void initLbName()
    {
        lbName.textProperty().bind(model.selectedSurvivorName);
    }

    @NodeInitializer
    private void initTextDesc()
    {
        textDesc.textProperty().bind(model.desc);
    }

}
