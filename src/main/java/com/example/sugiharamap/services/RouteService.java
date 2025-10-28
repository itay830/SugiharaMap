package com.example.sugiharamap.services;

import com.example.sugiharamap.customNodes.ArrowLine;
import com.example.sugiharamap.customNodes.Landmark;
import com.example.sugiharamap.customNodes.WorldMap;
import javafx.geometry.Bounds;

import java.util.List;
import java.util.Map;

public class RouteService {


    public static ArrowLine[] getRoutesWithCountries(WorldMap worldMap, List<String> countries) {
        ArrowLine[] routes = new ArrowLine[countries.size() - 1];
        for (int i = 0; i < routes.length; i++) {
            routes[i] = getRouteWithCountries(worldMap.getMapRoot().getBoundsInParent(), worldMap.getLandmarksMapByCountries(), countries.get(i), countries.get(i+1));
        }
        return routes;
    }

    public static ArrowLine getRouteWithCountries(Bounds parentBox, Map<String, Landmark> landmarks, String sCountry, String eCountry) {
        Landmark sLandmark = landmarks.get(sCountry);
        Landmark eLandmark = landmarks.get(eCountry);
        return new ArrowLine(
                sLandmark.getLandmarkX(),
                sLandmark.getLandmarkY(),
                eLandmark.getLandmarkX(),
                eLandmark.getLandmarkY());
    }
}
