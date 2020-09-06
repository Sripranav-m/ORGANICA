package com.example.dashboard.HelperClass;

public class SnackHelper {
    int image;
    String name,cost;

    public SnackHelper(int image, String name, String cost) {
        this.image = image;
        this.name = name;
        this.cost = cost;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
