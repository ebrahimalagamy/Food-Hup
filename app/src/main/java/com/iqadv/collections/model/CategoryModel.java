package com.iqadv.collections.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;



public class CategoryModel implements Parcelable {

    @SerializedName("cat_name")
    private String catName;
    @SerializedName("id")
    private String id;
    @SerializedName("cat_pic")
    private String catPic;


    protected CategoryModel(Parcel in) {
        catName = in.readString();
        id = in.readString();
        catPic = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getCatName() {
        return catName;
    }

    public String getId() {
        return id;
    }

    public String getCatPic() {
        return catPic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(catName);
        parcel.writeString(id);
        parcel.writeString(catPic);
    }
}