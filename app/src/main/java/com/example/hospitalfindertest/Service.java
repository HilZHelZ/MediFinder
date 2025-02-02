package com.example.hospitalfindertest;


public class Service {
    private String name;
    private int imageResourceId;

    public Service(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
