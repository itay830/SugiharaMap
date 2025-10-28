package com.example.sugiharamap.customNodes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

public class ArrowLine extends Line {
    public ArrowLine(Circle start, Circle end)
    {
        setStartX(start.getLayoutX());
        setStartY(start.getLayoutY());
        setEndX(end.getLayoutX());
        setEndY(end.getLayoutY());
        setStrokeWidth(2);
        setStroke(Color.BLUE.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setMouseTransparent(true);
        animation();
    }

    public ArrowLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        getStrokeDashArray().setAll(10.0, 5.0);
        setMouseTransparent(true);
        animation();
    }

    public ArrowLine(double startX, double startY, double endX, double endY) {
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
        setStrokeWidth(2);
        setStroke(Color.BLUE.deriveColor(0, 1, 1, 1));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        setMouseTransparent(true);
        animation();
    }

    private void animation()
    {
        getStrokeDashArray().addAll(10d, 5d);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(strokeDashOffsetProperty(), 30)),
                new KeyFrame(Duration.seconds(2), new KeyValue(strokeDashOffsetProperty(), 0))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
