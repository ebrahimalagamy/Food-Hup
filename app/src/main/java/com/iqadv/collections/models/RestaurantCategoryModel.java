package com.iqadv.collections.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantCategoryModel implements Parcelable {
    public static final Creator<RestaurantCategoryModel> CREATOR = new Creator<RestaurantCategoryModel>() {
        @Override
        public RestaurantCategoryModel createFromParcel(Parcel in) {
            return new RestaurantCategoryModel(in);
        }

        @Override
        public RestaurantCategoryModel[] newArray(int size) {
            return new RestaurantCategoryModel[size];
        }
    };
    private int id;
    private String categoryName;
    private String categoryDescription;
    private String categoryPrices;
    private String categoryRate;
    private String categoryNumberOfRate;
    private int categoryImage;

    public RestaurantCategoryModel(int id, String categoryName, String categoryDescription, String categoryPrices, String categoryRate, String categoryNumberOfRate, int categoryImage) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryPrices = categoryPrices;
        this.categoryRate = categoryRate;
        this.categoryNumberOfRate = categoryNumberOfRate;
        this.categoryImage = categoryImage;
    }

    protected RestaurantCategoryModel(Parcel in) {
        id = in.readInt();
        categoryName = in.readString();
        categoryDescription = in.readString();
        categoryPrices = in.readString();
        categoryRate = in.readString();
        categoryNumberOfRate = in.readString();
        categoryImage = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryPrices() {
        return categoryPrices;
    }

    public void setCategoryPrices(String categoryPrices) {
        this.categoryPrices = categoryPrices;
    }

    public String getCategoryRate() {
        return categoryRate;
    }

    public void setCategoryRate(String categoryRate) {
        this.categoryRate = categoryRate;
    }

    public String getCategoryNumberOfRate() {
        return categoryNumberOfRate;
    }

    public void setCategoryNumberOfRate(String categoryNumberOfRate) {
        this.categoryNumberOfRate = categoryNumberOfRate;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(categoryName);
        parcel.writeString(categoryDescription);
        parcel.writeString(categoryPrices);
        parcel.writeString(categoryRate);
        parcel.writeString(categoryNumberOfRate);
        parcel.writeInt(categoryImage);
    }
}
