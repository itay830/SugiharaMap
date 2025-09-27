package com.example.sugiharamap.utils;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class BoundLine extends Line {
    public BoundLine(Node start, Node end, boolean center) {
        System.out.println(start.boundsInParentProperty().get().getWidth() / 2);
        startXProperty().bind(start.layoutXProperty().add(start.boundsInParentProperty().get().getWidth() / 2));
        setStartY(start.getLayoutY() + start.getBoundsInParent().getHeight()/2);
        setEndX(end.getLayoutX() + end.getBoundsInParent().getWidth()/2);
        setEndY(end.getLayoutY() + end.getBoundsInParent().getHeight()/2);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 1));
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
