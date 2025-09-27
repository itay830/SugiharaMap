package com.example.sugiharamap.utils;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class BoundLine extends Line {
    public BoundLine(Node start, Node end, boolean center) {
        startXProperty().bind(start.layoutXProperty());
        setStartY(start.getLayoutY());
        setEndX(end.getLayoutX());
        setEndY(end.getLayoutY());
        setStrokeWidth(2);
        setStroke(Color.BLUE.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setMouseTransparent(true);
    }

    public BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        getStrokeDashArray().setAll(10.0, 5.0);
        setMouseTransparent(true);
    }

    public BoundLine(double startX, double startY, double endX, double endY) {
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setMouseTransparent(true);
    }
}
