package com.example.sugiharamap.models;


import java.util.LinkedHashMap;
import java.util.Map;

public class RouteStory {
    private String name;
    private LinkedHashMap<String, String> descByCountry;
    private String image;
    private String desc;
    private String distance;
    private String nationality;

    public RouteStory() {
    }

    public RouteStory(LinkedHashMap<String, String> descByCountry, String image) {
        this.descByCountry = descByCountry;
        this.image = image;
    }

    public Map<String, String> getDescByCountry() {
        return descByCountry;
    }

    public String getImage() {
        return image;
    }

    public void setDescByCountry(LinkedHashMap<String, String> descByCountry) {
        this.descByCountry = descByCountry;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDistance() {
        return distance;
    }

    public String getNationality() {
        return nationality;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int size()
    {
        return descByCountry.size();
    }

    @Override
    public String toString() {
        return name;
    }

}
