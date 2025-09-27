package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.Launcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import java.io.IOException;

public class PlacePoint extends VBox {

    private StringProperty country = new SimpleStringProperty(this, "country");

    @FXML
    private Text description;

    @FXML
    private Text countryName;

    @FXML
    private Circle circle;

    public PlacePoint() {
        build();
        initViews();
    }

    private void build() {
        FXMLLoader loader = new FXMLLoader(
                Launcher.class.getResource("PlacePoint.fxml"));
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
        initText();
        initCountryName();
        initCircle();
    }

    private void initCountryName() {
        countryName.textProperty().bind(country);
    }

    private void initText()
    {
        description.setVisible(false);
    }

    private void initCircle()
    {
        circle.setOnMouseEntered(event -> {
            description.setVisible(true);
        });
        circle.setOnMouseExited(event -> {
            description.setVisible(false);
        });
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
}
