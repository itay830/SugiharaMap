package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.models.RouteStory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    public List<RouteStory> routeStories = new ArrayList<>();

    public StringProperty selectedSurvivorName = new SimpleStringProperty();
    public StringProperty desc = new SimpleStringProperty();

}
