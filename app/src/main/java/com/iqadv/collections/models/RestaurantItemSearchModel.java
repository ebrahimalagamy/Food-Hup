package com.iqadv.collections.models;

public class RestaurantItemSearchModel {
    private int id;
    private String name;
    private String deliveryCosy;
    private String deliveryTime;
    private String category1;
    private String category2;
    private int image;

    public RestaurantItemSearchModel(int id, String name, String deliveryCosy, String deliveryTime, String category1, String category2, int image) {
        this.id = id;
        this.name = name;
        this.deliveryCosy = deliveryCosy;
        this.deliveryTime = deliveryTime;
        this.category1 = category1;
        this.category2 = category2;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryCosy() {
        return deliveryCosy;
    }

    public void setDeliveryCosy(String deliveryCosy) {
        this.deliveryCosy = deliveryCosy;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
