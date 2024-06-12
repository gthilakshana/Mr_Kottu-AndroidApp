package com.example.mr_kottu.Domain;



public class PopulerItems {
    private String name;
    private int imageResource;

    public PopulerItems(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
