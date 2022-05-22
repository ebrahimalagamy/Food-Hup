package com.iqadv.collections.model.restaurantDetails;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "food")
public class FoodDetailsModel implements Parcelable {

    public FoodDetailsModel(String resturantId, String price, String numberOfRatings, String catId, String name, String rating, String description, @NonNull String id, String pic) {
        this.resturantId = resturantId;
        this.price = price;
        this.numberOfRatings = numberOfRatings;
        this.catId = catId;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.id = id;
        this.pic = pic;
    }


    public static final Creator<FoodDetailsModel> CREATOR = new Creator<FoodDetailsModel>() {
        @Override
        public FoodDetailsModel createFromParcel(Parcel in) {
            return new FoodDetailsModel(in);
        }

        @Override
        public FoodDetailsModel[] newArray(int size) {
            return new FoodDetailsModel[size];
        }
    };

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNumberOfRatings(String numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @ColumnInfo(name = "resturant_id")
    @SerializedName("resturant_id")
    private String resturantId;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private String price;

    @ColumnInfo(name = "numberOfRatings")
    @SerializedName("number_of_ratings")
    private String numberOfRatings;

    @ColumnInfo(name = "catId")
    @SerializedName("cat_id")
    private String catId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private String rating;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "pic")
    @SerializedName("pic")
    private String pic;

    protected FoodDetailsModel(Parcel in) {
        resturantId = in.readString();
        price = in.readString();
        numberOfRatings = in.readString();
        catId = in.readString();
        name = in.readString();
        rating = in.readString();
        description = in.readString();
        id = in.readString();
        pic = in.readString();
    }

    public String getResturantId() {
        return resturantId;
    }

    public String getPrice() {
        return price;
    }

    public String getNumberOfRatings() {
        return numberOfRatings;
    }

    public String getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(resturantId);
        parcel.writeString(price);
        parcel.writeString(numberOfRatings);
        parcel.writeString(catId);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(description);
        parcel.writeString(id);
        parcel.writeString(pic);
    }
}