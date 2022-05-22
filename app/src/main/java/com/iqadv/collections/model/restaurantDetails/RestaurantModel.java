package com.iqadv.collections.model.restaurantDetails;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "restaurant")
public class RestaurantModel implements Parcelable {

    public RestaurantModel(String delivery, String coverPhoto, String address, String numberOfRatings, String name, String rating, String verified, @NonNull String id, String pic, String deliveryTime, String tags, String lat, String lng) {
        this.delivery = delivery;
        this.coverPhoto = coverPhoto;
        this.address = address;
        this.numberOfRatings = numberOfRatings;
        this.name = name;
        this.rating = rating;
        this.verified = verified;
        this.id = id;
        this.pic = pic;
        this.deliveryTime = deliveryTime;
        this.tags = tags;
        this.lat = lat;
        this.lng = lng;
    }

    @SerializedName("delivery")
    private String delivery;

    @SerializedName("cover_photo")
    private String coverPhoto;

    @SerializedName("address")
    private String address;

    @SerializedName("number_of_ratings")
    private String numberOfRatings;

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private String rating;

    @SerializedName("verified")
    private String verified;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("pic")
    private String pic;

    @SerializedName("delivery_time")
    private String deliveryTime;

    @SerializedName("tags")
    private String tags;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    protected RestaurantModel(Parcel in) {
        delivery = in.readString();
        coverPhoto = in.readString();
        address = in.readString();
        numberOfRatings = in.readString();
        name = in.readString();
        rating = in.readString();
        verified = in.readString();
        id = in.readString();
        pic = in.readString();
        deliveryTime = in.readString();
        tags = in.readString();
        lat = in.readString();
        lng = in.readString();
    }

    public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel in) {
            return new RestaurantModel(in);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberOfRatings() {
        return numberOfRatings;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getVerified() {
        return verified;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getTags() {
        return tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(delivery);
        parcel.writeString(coverPhoto);
        parcel.writeString(address);
        parcel.writeString(numberOfRatings);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(verified);
        parcel.writeString(id);
        parcel.writeString(pic);
        parcel.writeString(deliveryTime);
        parcel.writeString(tags);
        parcel.writeString(lat);
        parcel.writeString(lng);
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberOfRatings(String numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}