package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.Launcher;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import java.io.IOException;

public class PlacePoint extends VBox {

    private StringProperty country = new SimpleStringProperty(this, "country");
    private StringProperty description = new SimpleStringProperty(this, "description");
    private BooleanProperty isTextVisible = new SimpleBooleanProperty(false);
    private DoubleProperty centerX = new SimpleDoubleProperty(0);
    private DoubleProperty centerY = new SimpleDoubleProperty(0);


    @FXML
    private VBox vbTextCont;

    @FXML
    private Text txtDescription;

    @FXML
    private Text txtCountryName;

    @FXML
    private Circle circle;

    public PlacePoint() {
        build();
        initViews();
    }

    private void build() {
        FXMLLoader loader = new FXMLLoader(
                Launcher.class.getResource("landmark.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initViews()
    {
        initTxtCountryName();
        initTxtDescription();
        initVbTextCont();
        initCircle();
    }

    private void initVbTextCont() {
        vbTextCont.visibleProperty().bind(isTextVisible);
    }

    private void initTxtCountryName() {
        txtCountryName.textProperty().bind(country);
    }

    private void initCircle()
    {
        circle.setOnMouseEntered(event -> {
            isTextVisible.set(true);
        });
        circle.setOnMouseExited(event -> {
            isTextVisible.set(false);
        });
        double dx = getLayoutBounds().getWidth() - circle.getRadius();
        double dy = getLayoutBounds().getHeight() - circle.getRadius();
        setLayoutX(centerX.get() - dx);
        setLayoutX(centerY.get() - dy);
    }

    private void initTxtDescription()
    {
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }


    public double getCenterX() {
        return centerX.get();
    }

    public DoubleProperty centerXProperty() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX.set(centerX);
    }

    public double getCenterY() {
        return centerY.get();
    }

    public DoubleProperty centerYProperty() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY.set(centerY);
    }
}
