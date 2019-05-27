package com.stackroute.usertrackservice.controller;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usertrackservice")
public class UserTrackController {
    private UserTrackService userTrackService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserTrackController(UserTrackService userTrackService) {
        this.userTrackService = userTrackService;
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) throws UserAlreadyExistsException{
        try{
            User userObj = userTrackService.registerUser(user);
            responseEntity = new ResponseEntity(userObj, HttpStatus.CREATED);
        }catch(UserAlreadyExistsException uaee){
            throw uaee;
        }
        return responseEntity;
    }

    @PostMapping("/user/{userName}/track")
    public ResponseEntity saveUserTracktoWishList(@RequestBody Track track,@PathVariable("userName") String userName)
    throws TrackAlreadyExistsException {
        try{
            User user = userTrackService.saveTrackToWishList(track,userName);
            responseEntity = new ResponseEntity(user,HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException taee){
            throw taee;
        }catch(Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/{userName}/track")
    public ResponseEntity deleteUserTracktoWishList(@RequestBody Track track,@PathVariable("userName") String userName)
            throws TrackNotFoundException {
        try{
            User user = userTrackService.deleteTrackFromWishList(track.getTrackId(),userName);
            responseEntity = new ResponseEntity(user,HttpStatus.OK);
        }catch(TrackNotFoundException tnfe){
            throw tnfe;
        }catch(Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PatchMapping("/user/{userName}/track")
    public ResponseEntity updateCommentsForUserTrack(@RequestBody Track track,@PathVariable("userName") String userName)
            throws TrackNotFoundException {
        try{
            User user = userTrackService.updateCommentForTrack(track.getTrackId(),track.getComments(),userName);
            responseEntity = new ResponseEntity(user,HttpStatus.OK);
        }catch(TrackNotFoundException tnfe){
            throw tnfe;
        }catch(Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/{userName}/tracks")
    public ResponseEntity getAllTracks(@PathVariable("userName") String userName){
        try{
            responseEntity = new ResponseEntity(userTrackService.getTracksFromWishList(userName),HttpStatus.OK);
        }catch(Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
