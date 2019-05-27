package com.stackroute.usertrackservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Image {
    @Id
    private int imageId;

    @JsonProperty("url")
    private String imageUrl;

    @JsonProperty("size")
    private String imageSize;

    public Image() {
    }

    public Image(int imageId, String imageUrl, String imageSize) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.imageSize = imageSize;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageSize='" + imageSize + '\'' +
                '}';
    }
}