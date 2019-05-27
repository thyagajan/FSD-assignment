package com.stackroute.musixservice.service;

import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.exception.TrackAlreadyExistsException;
import com.stackroute.musixservice.exception.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.musixservice.repository.MusixRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MusixServiceImpl implements MusixService {

    @Autowired
    MusixRepository musixRepo;

    @Override
    public Track saveTrackToWishList(Track track) throws TrackAlreadyExistsException {
        Optional<Track> optional = musixRepo.findById(track.getTrackId());
        if(optional.isPresent())
            throw new TrackAlreadyExistsException();
        musixRepo.insert(track);
        return track;
    }

    @Override
    public boolean deleteTrackFromWishList(String id) throws TrackNotFoundException {
        Optional<Track> optional = musixRepo.findById(id);
        if(!optional.isPresent())
            throw new TrackNotFoundException();
        musixRepo.deleteById(id);
        return true;
    }

    @Override
    public Track updateCommentForTrack(String id, String comments) throws TrackNotFoundException {
        Optional<Track> optional = musixRepo.findById(id);
        if(!optional.isPresent())
            throw new TrackNotFoundException();
        Track track = optional.get();
        track.setComments(comments);
        musixRepo.save(track);
        return track;
    }

    @Override
    public List<Track> getTracksFromWishList() throws Exception {
        return musixRepo.findAll();
    }
}
