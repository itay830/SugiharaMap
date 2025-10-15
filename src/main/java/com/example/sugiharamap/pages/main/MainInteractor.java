package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.models.RouteStory;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainInteractor {
    private final MainModel model;

    public MainInteractor(MainModel model) {
        this.model = model;
        initRouteStories();

    }

    public void initRouteStories()
    {
        String path = "src/main/resources/com/example/sugiharamap/bertieFrankel.json";
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            Gson gson = new Gson();
            model.routeStories.add(gson.fromJson(reader, RouteStory.class));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
