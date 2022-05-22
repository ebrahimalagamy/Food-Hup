package com.iqadv.collections.models.googleModel;

import java.util.List;

public class GooglePlaceModel {
    private List<String> types;
    private String businessStatus;
    private String icon;
    private double rating;
    private String iconBackgroundColor;
    private List<PhotosItem> photos;
    private String reference;
    private int userRatingsTotal;
    private int priceLevel;
    private String scope;
    private String name;
    private OpeningHours openingHours;
    private Geometry geometry;
    private String iconMaskBaseUri;
    private String vicinity;
    private PlusCode plusCode;
    private String placeId;
    private boolean permanentlyClosed;

    public List<String> getTypes() {
        return types;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public String getIcon() {
        return icon;
    }

    public double getRating() {
        return rating;
    }

    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    public List<PhotosItem> getPhotos() {
        return photos;
    }

    public String getReference() {
        return reference;
    }

    public int getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public String getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    public String getVicinity() {
        return vicinity;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public String getPlaceId() {
        return placeId;
    }

    public boolean isPermanentlyClosed() {
        return permanentlyClosed;
    }
}