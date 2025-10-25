package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.models.RouteStory;
import com.example.sugiharamap.utils.filesUtil.FilesService;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public record MainInteractor(MainModel model) {
    public MainInteractor(MainModel model) {
        this.model = model;
        initRouteStories();
    }

    public void initRouteStories() {
        model.routeStories.clear();
        String[] names = FilesService.getAllFileNameInDir(FilesService.survivorsPath);
        Gson gson = new Gson();
        for (var name : names)
        {
            String path = FilesService.survivorsPath.concat(name);
            try (Reader reader = Files.newBufferedReader(Paths.get(path))){
                RouteStory routeStory = gson.fromJson(reader, RouteStory.class);
                model.routeStories.add(routeStory);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
