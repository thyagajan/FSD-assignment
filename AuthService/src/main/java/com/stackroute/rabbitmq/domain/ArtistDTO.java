package com.stackroute.rabbitmq.domain;

public class ArtistDTO {
  private int artistId;

  private String artistName;

  private String artistUrl;

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

  public ImageDTO getImage() {
    return image;
  }

  public void setImage(ImageDTO image) {
    this.image = image;
  }

  public ArtistDTO(int artistId, String artistName, String artistUrl, ImageDTO image) {
    this.artistId = artistId;
    this.artistName = artistName;
    this.artistUrl = artistUrl;
    this.image = image;
  }

  private ImageDTO image;

}
