package com.example.sugiharamap.customNodes;

import com.example.sugiharamap.Launcher;
import com.example.sugiharamap.utils.mvciUtil.ViewBuilder;
import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class Landmark extends VBox {

    private final StringProperty country = new SimpleStringProperty(this, "country");
    private final StringProperty description = new SimpleStringProperty(this, "description");
    private final BooleanProperty isTextVisible = new SimpleBooleanProperty(false);
    private final DoubleProperty landmarkX = new SimpleDoubleProperty();
    private final DoubleProperty landmarkY = new SimpleDoubleProperty();

    @FXML
    private VBox vbTextCont;

    @FXML
    private Text txtDescription;

    @FXML
    private Text txtCountryName;

    @FXML
    private Circle circle;

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

    protected void initViews() {
        try {
            for (var method : ViewBuilder.initialize(getClass())) {
                method.invoke(this);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Landmark() {
        build();
        initViews();
    }

    @NodeInitializer
    private void initVbTextCont() {
        vbTextCont.visibleProperty().bind(isTextVisible);
    }

    @NodeInitializer
    private void initTxtCountryName() {
        txtCountryName.textProperty().bind(country);
    }

    @NodeInitializer
    private void initCircle() {
        setViewOrder(0);
        circle.setOnMouseEntered(event -> {
            isTextVisible.set(true);
            setViewOrder(-1);
        });
        circle.setOnMouseExited(event -> {
            isTextVisible.set(false);
            setViewOrder(0);
        });
    }

    @NodeInitializer
    private void initTxtDescription() {
        txtDescription.textProperty().bind(descriptionProperty());
    }


    public void setPosition(double x, double y) {
        landmarkX.set(x);
        landmarkY.set(y);
        updatePosition();
    }

    public void updatePosition() {
        Bounds bounds = getParent().localToScene(getBoundsInParent());
        double dx = getWidth() / 2;
        double dy = getHeight() - circle.getRadius();
        setLayoutX(landmarkX.get() - dx);
        setLayoutY(landmarkY.get() - dy);
    }


    public Circle getCircle() {
        return circle;
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


    public double getLandmarkX() {
        return landmarkX.get();
    }

    public DoubleProperty landmarkXProperty() {
        return landmarkX;
    }

    public void setLandmarkX(double landmarkX) {
        this.landmarkX.set(landmarkX);
    }

    public double getLandmarkY() {
        return landmarkY.get();
    }

    public DoubleProperty landmarkYProperty() {
        return landmarkY;
    }

    public void setLandmarkY(double landmarkY) {
        this.landmarkY.set(landmarkY);
    }
}