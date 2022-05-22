package com.iqadv.collections.models;

public class UpcomingOderModel {
    private int id;
    private String orderNumber;
    private String numberOfItem;
    private String restName;
    private String arrivalTime;
    private String orderStatus;
    private int restUpcomingImage;
    private OrderDetailsModel orderDetailsModel;

    public UpcomingOderModel(int id, String orderNumber, String numberOfItem, String restName, String arrivalTime, String orderStatus, int restUpcomingImage, OrderDetailsModel orderDetailsModel) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.numberOfItem = numberOfItem;
        this.restName = restName;
        this.arrivalTime = arrivalTime;
        this.orderStatus = orderStatus;
        this.restUpcomingImage = restUpcomingImage;
        this.orderDetailsModel = orderDetailsModel;
    }

    public int getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getNumberOfItem() {
        return numberOfItem;
    }

    public String getRestName() {
        return restName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getRestUpcomingImage() {
        return restUpcomingImage;
    }

    public OrderDetailsModel getOrderDetailsModel() {
        return orderDetailsModel;
    }
}
