package com.stackroute.musixservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Artist {

    @Id
    private int artistId;

    @JsonProperty("name")
    private String artistName;

    @JsonProperty("url")
    private String artistUrl;

    private Image image;

    public Artist() {
    }

    public Artist(int artistId, String artistName, String artistUrl, Image image) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistUrl = artistUrl;
        this.image = image;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", artistUrl='" + artistUrl + '\'' +
                ", image=" + image +
                '}';
    }
}
