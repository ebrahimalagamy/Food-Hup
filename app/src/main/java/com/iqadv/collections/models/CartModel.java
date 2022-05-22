package com.iqadv.collections.models;

public class CartModel {

    private int cart_image;
    private String cart_name;
    private String cart_description;
    private String cart_prise;

    public CartModel(int cart_image, String cart_name, String cart_description, String cart_prise) {
        this.cart_image = cart_image;
        this.cart_name = cart_name;
        this.cart_description = cart_description;
        this.cart_prise = cart_prise;
    }

    public int getCart_image() {
        return cart_image;
    }

    public void setCart_image(int cart_image) {
        this.cart_image = cart_image;
    }

    public String getCart_name() {
        return cart_name;
    }

    public void setCart_name(String cart_name) {
        this.cart_name = cart_name;
    }

    public String getCart_description() {
        return cart_description;
    }

    public void setCart_description(String cart_description) {
        this.cart_description = cart_description;
    }

    public String getCart_prise() {
        return cart_prise;
    }

    public void setCart_prise(String cart_prise) {
        this.cart_prise = cart_prise;
    }
}
