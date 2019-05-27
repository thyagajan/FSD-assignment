package com.stackroute.usertrackservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="Tracks")
public class Track {

    @Id
    private String trackId;

    @JsonProperty("name")
    private String trackName;

    private String comments;

    @JsonProperty("listners")
    private String trackListners;

    @JsonProperty("url")
    private String url;

    private Artist artist;

    public Track() {
    }

    public Track(String trackId, String trackName, String comments, String trackListners, String url, Artist artist) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.comments = comments;
        this.trackListners = trackListners;
        this.url = url;
        this.artist = artist;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTrackListners() {
        return trackListners;
    }

    public void setTrackListners(String trackListners) {
        this.trackListners = trackListners;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", comments='" + comments + '\'' +
                ", trackListners='" + trackListners + '\'' +
                ", url='" + url + '\'' +
                ", artist=" + artist +
                '}';
    }
}