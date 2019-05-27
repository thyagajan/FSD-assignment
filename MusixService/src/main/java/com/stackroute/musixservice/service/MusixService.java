package com.stackroute.musixservice.service;

import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.exception.TrackAlreadyExistsException;
import com.stackroute.musixservice.exception.TrackNotFoundException;

import java.util.List;

public interface MusixService {

    Track saveTrackToWishList(Track track) throws TrackAlreadyExistsException;
    boolean deleteTrackFromWishList(String id) throws TrackNotFoundException;
    Track updateCommentForTrack(String id, String comments) throws TrackNotFoundException;
    List<Track> getTracksFromWishList() throws Exception;
}
