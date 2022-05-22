package com.iqadv.collections.models;

public class HistoryOrderModel {
    private int id;
    private String date;
    private String numberOfItem;
    private String restName;
    private String historyStatus;
    private String historyPrise;
    private int restHistoryImage;

    public HistoryOrderModel(int id, String date, String numberOfItem, String restName, String historyStatus, String historyPrise, int restHistoryImage) {
        this.id = id;
        this.date = date;
        this.numberOfItem = numberOfItem;
        this.restName = restName;
        this.historyStatus = historyStatus;
        this.historyPrise = historyPrise;
        this.restHistoryImage = restHistoryImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(String numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getHistoryStatus() {
        return historyStatus;
    }

    public void setHistoryStatus(String historyStatus) {
        this.historyStatus = historyStatus;
    }

    public String getHistoryPrise() {
        return historyPrise;
    }

    public void setHistoryPrise(String historyPrise) {
        this.historyPrise = historyPrise;
    }

    public int getRestHistoryImage() {
        return restHistoryImage;
    }

    public void setRestHistoryImage(int restHistoryImage) {
        this.restHistoryImage = restHistoryImage;
    }
}
