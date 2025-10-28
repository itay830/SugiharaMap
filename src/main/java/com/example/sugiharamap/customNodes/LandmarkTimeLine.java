package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.models.RouteStory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class LandmarkTimeLine extends HBox {
    private ObjectProperty<RouteStory> routeStory = new SimpleObjectProperty<>();

    public LandmarkTimeLine() {
        setAlignment(Pos.CENTER);
        setHgrow(this, Priority.ALWAYS);
        setSpacing(50);
        routeStory.addListener((observable, oldValue, newValue) ->
                update());
    }


    public void bindRouteStories(ObjectProperty<RouteStory> toBound) {
        routeStory.bind(toBound);

    }

    private void update() {
        getChildren().clear();
        for (var node : routeStory.get().getDescByCountry().values()) {
            getChildren().add(new Rectangle(75, 75, Color.BLUEVIOLET));
        }
    }


    public RouteStory getRouteStory() {
        return routeStory.get();
    }

    public ObjectProperty<RouteStory> routeStoryProperty() {
        return routeStory;
    }

    public void setRouteStory(RouteStory routeStory) {
        this.routeStory.set(routeStory);
    }
}
