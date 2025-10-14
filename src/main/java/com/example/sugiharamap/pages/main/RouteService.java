package com.example.sugiharamap.pages.main;

import com.example.sugiharamap.customNodes.BoundLine;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.HashMap;
import java.util.Map;

public class RouteService {
    private static Map<String, Circle> nodePlaces = null;

    public static void init(Pane parent) {
        if (nodePlaces == null) {
            nodePlaces = new HashMap<>();
            parent.getChildren().forEach(node -> {
                if (node instanceof Shape && !node.getAccessibleText().isBlank()) {
                    nodePlaces.put(node.getAccessibleText().strip(), (Circle) node);
                }
            });
            return;
        }
        throw new RuntimeException("Can't init second time :3");
    }

    public static Pane getRoutes(String... places) {
        if (nodePlaces == null) {
            throw new RuntimeException("RouteService isn't initialized :<");
        }
        Line[] lines = new Line[places.length - 1];
        for (int i = 0; i < places.length - 1; i++) {
            lines[i] = new BoundLine(nodePlaces.get(places[i]), nodePlaces.get(places[i + 1]));
        }
        return new Pane(lines);
    }
}
