package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserTrackService {
    User saveTrackToWishList(Track track, String userName) throws TrackAlreadyExistsException;
    User deleteTrackFromWishList(String id, String userName) throws TrackNotFoundException;
    User updateCommentForTrack(String id, String comments, String userName) throws TrackNotFoundException;
    List<Track> getTracksFromWishList(String userName) throws Exception;
    User registerUser(User user) throws UserAlreadyExistsException;
}
