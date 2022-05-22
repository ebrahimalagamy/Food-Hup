package com.iqadv.collections.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MainCategoryModel implements Parcelable {
    public static final Creator<MainCategoryModel> CREATOR = new Creator<MainCategoryModel>() {
        @Override
        public MainCategoryModel createFromParcel(Parcel in) {
            return new MainCategoryModel(in);
        }

        @Override
        public MainCategoryModel[] newArray(int size) {
            return new MainCategoryModel[size];
        }
    };
    private int image;
    private String name;

    public MainCategoryModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    protected MainCategoryModel(Parcel in) {
        image = in.readInt();
        name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(name);
    }
}
