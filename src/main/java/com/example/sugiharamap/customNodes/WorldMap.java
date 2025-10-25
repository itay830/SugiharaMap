package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.models.RouteStory;
import com.example.sugiharamap.services.RouteService;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.*;

public class WorldMap extends ZoomableScrollPane {
    protected void initViews() {
        try {
            for (var method : ViewBuilder.initialize(getClass())) {
                method.invoke(this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final StackPane mapRoot;
    private Pane pRoutes;
    private Pane pCountries;
    private final Map<String, Landmark> landmarksMapByCountries = new HashMap<>();


    public WorldMap() {
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

    @NodeInitializer(order = 0)
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
            landmarksMapByCountries.put(child.getAccessibleText(), landmark);
        }
    }

    @NodeInitializer
    private void initPRoutes() {
        pRoutes = (Pane) mapRoot.lookup("#pRoutes");
    }

    public void setRoutes(BoundLine... boundLines) {
        pRoutes.getChildren().clear();
        pRoutes.getChildren().addAll(boundLines);
    }

    public void setRoutesByCountries(List<String> countries) {
        setRoutes(RouteService.getRoutesWithCountries(this, countries));
    }

    public void setRoutesByRouteStory(RouteStory routeStory) {
        pRoutes.getChildren().clear();

        for (Map.Entry<String, Landmark> entry : landmarksMapByCountries.entrySet()) {
            String desc = routeStory.getDescByCountry().get(entry.getKey());
            if (desc != null) {
                entry.getValue().setDescription(desc);
            }
        }
        Platform.runLater(() -> {
            for (var value : landmarksMapByCountries.values()) {
                value.updatePosition();
            }
            setRoutes(RouteService.getRoutesWithCountries(this,
                    new ArrayList<>(routeStory.getDescByCountry().keySet())));
        });

    }


    public Collection<Landmark> getLandmarksCollection() {
        return landmarksMapByCountries.values();
    }

    public Map<String, Landmark> getLandmarksMapByCountries() {
        return landmarksMapByCountries;
    }

    public StackPane getMapRoot() {
        return mapRoot;
    }
}
