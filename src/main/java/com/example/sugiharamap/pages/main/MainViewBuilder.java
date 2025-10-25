package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.customNodes.WorldMap;
import com.example.sugiharamap.models.RouteStory;
import com.example.sugiharamap.utils.filesUtil.FilesService;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.File;
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
    @FXML
    private ImageView ivImageFrame;
    @FXML
    private Label lbLandmarks, lbStart, lbEnd, lbDistance, lbNationality;
    @FXML
    private ToggleButton tbContext;
    @FXML
    private VBox vbContext;


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
        mapContainer.getChildren().addFirst(worldMap);
        worldMap.setRoutesByRouteStory(model.routeStories.getFirst());
    }

    @NodeInitializer
    private void initChSurvivors() {
        for (var route : model.routeStories) {
            cbSurvivors.getItems().add(route);
        }
        cbSurvivors.setOnAction((e) -> {
            var selectionItem = cbSurvivors.getSelectionModel().getSelectedItem();
            int selectedIndex = cbSurvivors.getSelectionModel().getSelectedIndex();
            worldMap.setRoutesByRouteStory(model.routeStories.get(selectedIndex));
            model.selectedSurvivorName.set(selectionItem.getName());
            model.desc.set(selectionItem.getDesc());
            model.imageProperty.set(new Image(
                    new File(
                            FilesService.imagesPath + selectionItem.getImage())
                            .toURI().toString()));
            model.landMarksCount.set("Landmarks Count: " + selectionItem.size());
            Object[] values = selectionItem.getDescByCountry().keySet().toArray();
            model.start.set("Start: " + values[0].toString());
            model.end.set("End: " + values[values.length-1].toString());
            model.nationality.set("Nationality: " + selectionItem.getNationality());
            model.distance.set("Distance: " + selectionItem.getDistance());
        });
        cbSurvivors.getSelectionModel().selectFirst();
    }

    @NodeInitializer
    private void initLbName() {
        lbName.textProperty().bind(model.selectedSurvivorName);
    }

    @NodeInitializer
    private void initTextDesc() {
        textDesc.textProperty().bind(model.desc);
    }

    @NodeInitializer()
    private void initIvImageFrame() {
//        ivImageFrame.fitWidthProperty().bind(((VBox)ivImageFrame.getParent()).widthProperty());
//        ivImageFrame.fitHeightProperty().bind(((VBox)ivImageFrame.getParent()).heightProperty());
        ivImageFrame.imageProperty().bind(model.imageProperty);
    }

    @NodeInitializer
    private void initLbs()
    {
        lbLandmarks.textProperty().bind(model.landMarksCount);
        lbDistance.textProperty().bind(model.distance);
        lbStart.textProperty().bind(model.start);
        lbEnd.textProperty().bind(model.end);
        lbNationality.textProperty().bind(model.nationality);
    }

    @NodeInitializer
    private void initVbContext()
    {
        vbContext.visibleProperty().bind(tbContext.selectedProperty());
    }
}
