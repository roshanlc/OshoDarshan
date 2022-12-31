package com.roshanam.oshodarshan.ui.utils;

public class Album {
    private String name;
    private String url;

    public Album() {
        // constructor
    }
    public Album(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
