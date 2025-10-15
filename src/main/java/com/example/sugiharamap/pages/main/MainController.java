package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.utils.mvciUtil.Controller;
import javafx.scene.layout.Region;

public class MainController extends Controller {
    private final MainModel model;
    private final MainViewBuilder viewBuilder;
    private final MainInteractor interactor;

    public MainController() {
        this.model = new MainModel();
        this.interactor = new MainInteractor(model);
        this.viewBuilder = new MainViewBuilder(model);

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
