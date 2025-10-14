package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.utils.mvciUtil.Controller;
import javafx.scene.layout.Region;

public class MainController extends Controller {
    MainViewBuilderBeta viewBuilder;

    public MainController() {
        this.viewBuilder = new MainViewBuilderBeta();
    }

    public Region getView()
    {
        return viewBuilder.build();
    }

    public void initViews()
    {
        viewBuilder.initViews();
    }
}
