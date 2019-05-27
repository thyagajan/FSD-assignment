package com.stackroute.musixservice.controller;

import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.exception.TrackAlreadyExistsException;
import com.stackroute.musixservice.exception.TrackNotFoundException;
import com.stackroute.musixservice.service.MusixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/musixservice")
public class MusixServiceController {

    private MusixService musicService;
    private ResponseEntity responseEntity;

    @Autowired
    public MusixServiceController(MusixService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("track")
    @ExceptionHandler(TrackAlreadyExistsException.class)
    ResponseEntity saveTrackToWishList(@RequestBody Track track) throws TrackAlreadyExistsException{
        try{
            musicService.saveTrackToWishList(track);
            responseEntity = new ResponseEntity(track, HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException t){
            throw t;
        }catch(Exception ex){
            responseEntity = new ResponseEntity("Error..Try After some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @DeleteMapping("track/{id}")
    ResponseEntity deleteTrackFromWishList(@PathVariable("id") String id) throws TrackNotFoundException {
        try{
            musicService.deleteTrackFromWishList(id);
            responseEntity = new ResponseEntity("Successfully Deleted", HttpStatus.OK);
        }catch(TrackNotFoundException t){
            throw t;
        }catch(Exception ex){
            responseEntity = new ResponseEntity("Error..Try After some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @PutMapping("track/{id}")
    ResponseEntity updateCommentsForTrack(@RequestBody Track track,@PathVariable("id") String id) throws TrackNotFoundException {
        try{
            musicService.updateCommentForTrack(id,track.getComments());
            responseEntity = new ResponseEntity(track, HttpStatus.OK);
        }catch(TrackNotFoundException t){
            throw t;
        }catch(Exception ex){
            responseEntity = new ResponseEntity("Error..Try After some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("tracks")
    ResponseEntity getTracksFromWishList() throws TrackNotFoundException {
        try{
            List<Track> list = musicService.getTracksFromWishList();
            responseEntity = new ResponseEntity(list, HttpStatus.OK);
        }catch(TrackNotFoundException t){
            throw t;
        }catch(Exception ex){
            responseEntity = new ResponseEntity("Error..Try After some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
}
