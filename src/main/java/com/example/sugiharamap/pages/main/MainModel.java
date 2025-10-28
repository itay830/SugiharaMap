package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.models.RouteStory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    public ObjectProperty<List<RouteStory>> routeStoriesProperty = new SimpleObjectProperty<>();

    public StringProperty selectedSurvivorName = new SimpleStringProperty();
    public StringProperty desc = new SimpleStringProperty();
    public StringProperty landMarksCount = new SimpleStringProperty();
    public StringProperty start = new SimpleStringProperty();
    public StringProperty end = new SimpleStringProperty();
    public StringProperty nationality = new SimpleStringProperty();
    public StringProperty distance = new SimpleStringProperty();
    public ObjectProperty<RouteStory> currRouteStory = new SimpleObjectProperty<>();

    public ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    public MainModel()
    {
        routeStoriesProperty.set(new ArrayList<>());
    }

}
