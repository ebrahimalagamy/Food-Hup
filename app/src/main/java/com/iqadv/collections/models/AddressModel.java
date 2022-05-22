package com.iqadv.collections.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressModel implements Parcelable {
    public static final Creator<AddressModel> CREATOR = new Creator<AddressModel>() {
        @Override
        public AddressModel createFromParcel(Parcel in) {
            return new AddressModel(in);
        }

        @Override
        public AddressModel[] newArray(int size) {
            return new AddressModel[size];
        }
    };
    private int id = 1;
    private String fullName;
    private String mobileNumber;
    private String state;
    private String city;
    private String street;

    public AddressModel(int id, String fullName, String mobileNumber, String state, String city, String street) {
        this.id = id;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    protected AddressModel(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        mobileNumber = in.readString();
        state = in.readString();
        city = in.readString();
        street = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(fullName);
        parcel.writeString(mobileNumber);
        parcel.writeString(state);
        parcel.writeString(city);
        parcel.writeString(street);
    }
}
