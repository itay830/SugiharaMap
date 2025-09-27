package com.example.sugiharamap.main;

import javafx.scene.Parent;

public class MainController {
    MainViewBuilder viewBuilder;

    public MainController() {
        this.viewBuilder = new MainViewBuilder();
    }

    public Parent getView()
    {
        return viewBuilder.build();
    }

    public void initViews()
    {
        viewBuilder.initViews();
    }
}
