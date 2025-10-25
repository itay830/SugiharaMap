package com.example.sugiharamap.models;


import java.util.Map;

public class RouteStory {
    private String name;
    private Map<String, String> descByCountry;
    private String image;
    private String desc;

    public RouteStory() {
    }

    public RouteStory(Map<String, String> descByCountry, String image) {
        this.descByCountry = descByCountry;
        this.image = image;
    }

    public Map<String, String> getDescByCountry() {
        return descByCountry;
    }

    public String getImage() {
        return image;
    }

    public void setDescByCountry(Map<String, String> descByCountry) {
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

    @Override
    public String toString() {
        return name;
    }
}
