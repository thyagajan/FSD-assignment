package com.stackroute.rabbitmq.domain;

public class TrackDTO {
  private String trackId;

  private String trackName;

  private String comments;

  private String trackListners;

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

  public ArtistDTO getArtist() {
    return artist;
  }

  public void setArtist(ArtistDTO artist) {
    this.artist = artist;
  }

  public TrackDTO(String trackId, String trackName, String comments, String trackListners, String url, ArtistDTO artist) {
    this.trackId = trackId;
    this.trackName = trackName;
    this.comments = comments;
    this.trackListners = trackListners;
    this.url = url;
    this.artist = artist;
  }

  private String url;

  private ArtistDTO artist;

}
