package com.iqadv.collections.models;

public class OrderDetailsModel {
    private String DeliveryLat;
    private String DeliveryLng;
    private String distance;
    private String deliveryPlace;
    private String orderConfirmed;
    private String orderConfirmedDate;
    private String preparingFood;
    private String preparingFoodDate;
    private String FoodInTheWay;
    private String FoodInTheWayDate;
    private String DeliveredToYou;
    private String DeliveredToYouDate;
    private String DeliveryName;
    private String DeliveryID;
    private String DeliveryNumber;
    private int DeliveryImage;

    public OrderDetailsModel(String deliveryLat, String deliveryLng, String distance, String deliveryPlace, String orderConfirmed, String orderConfirmedDate, String preparingFood, String preparingFoodDate, String foodInTheWay, String foodInTheWayDate, String deliveredToYou, String deliveredToYouDate, String deliveryName, String deliveryID, String deliveryNumber, int deliveryImage) {
        DeliveryLat = deliveryLat;
        DeliveryLng = deliveryLng;
        this.distance = distance;
        this.deliveryPlace = deliveryPlace;
        this.orderConfirmed = orderConfirmed;
        this.orderConfirmedDate = orderConfirmedDate;
        this.preparingFood = preparingFood;
        this.preparingFoodDate = preparingFoodDate;
        FoodInTheWay = foodInTheWay;
        FoodInTheWayDate = foodInTheWayDate;
        DeliveredToYou = deliveredToYou;
        DeliveredToYouDate = deliveredToYouDate;
        DeliveryName = deliveryName;
        DeliveryID = deliveryID;
        DeliveryNumber = deliveryNumber;
        DeliveryImage = deliveryImage;
    }

    public String getDeliveryLat() {
        return DeliveryLat;
    }

    public String getDeliveryLng() {
        return DeliveryLng;
    }

    public String getDistance() {
        return distance;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public String getOrderConfirmed() {
        return orderConfirmed;
    }

    public String getOrderConfirmedDate() {
        return orderConfirmedDate;
    }

    public String getPreparingFood() {
        return preparingFood;
    }

    public String getPreparingFoodDate() {
        return preparingFoodDate;
    }

    public String getFoodInTheWay() {
        return FoodInTheWay;
    }

    public String getFoodInTheWayDate() {
        return FoodInTheWayDate;
    }

    public String getDeliveredToYou() {
        return DeliveredToYou;
    }

    public String getDeliveredToYouDate() {
        return DeliveredToYouDate;
    }

    public String getDeliveryName() {
        return DeliveryName;
    }

    public String getDeliveryID() {
        return DeliveryID;
    }

    public String getDeliveryNumber() {
        return DeliveryNumber;
    }

    public int getDeliveryImage() {
        return DeliveryImage;
    }
}
