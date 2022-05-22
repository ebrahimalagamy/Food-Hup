package com.iqadv.collections.models.googleModel;

import java.util.List;

public class PhotosItem {
    private String photoReference;
    private int width;
    private List<String> htmlAttributions;
    private int height;

    public String getPhotoReference() {
        return photoReference;
    }

    public int getWidth() {
        return width;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public int getHeight() {
        return height;
    }
}