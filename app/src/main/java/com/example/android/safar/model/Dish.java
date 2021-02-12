package com.example.android.safar.model;

public class Dish {

    private final String name;

    private final int imageResId;

    public Dish(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

}





