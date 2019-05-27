package com.stackroute.usertrackservice.service;

import com.stackroute.rabbitmq.domain.TrackDTO;
import com.stackroute.rabbitmq.domain.UserDTO;
import com.stackroute.usertrackservice.config.Producer;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrackServiceImpl implements UserTrackService {

    private UserTrackRepository userTrackRepository;
    private Producer producer;

    @Autowired
    public UserTrackServiceImpl(UserTrackRepository userTrackRepository,Producer producer){
        this.userTrackRepository= userTrackRepository;
        this.producer = producer;
    }

    @Override
    public User saveTrackToWishList(Track track, String userName) throws TrackAlreadyExistsException {
        User userObj = userTrackRepository.findByUserName(userName);
        List<Track> trackList = userObj.getTrackList();
        if(trackList != null){
            for(Track trackObj:trackList){
               if(trackObj.getTrackId().equals(track.getTrackId()))
                   throw new TrackAlreadyExistsException();
            }
            trackList.add(track);
            userObj.setTrackList(trackList);
            userTrackRepository.save(userObj);
        }else{
            trackList = new ArrayList<>();
            trackList.add(track);
            userObj.setTrackList(trackList);
            userTrackRepository.save(userObj);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userObj.getUserName());
        userDTO.setEmail(userObj.getEmail());
        userDTO.setPassword(userObj.getPassword());
        List  trackDTOS = new ArrayList<>();
        trackDTOS.add(track);
        userDTO.setTrackList(trackDTOS);
        producer.sendTrackToRabbitMQ(userDTO);
        return userObj;
    }

    @Override
    public User deleteTrackFromWishList(String id, String userName) throws TrackNotFoundException {
        User userObj = userTrackRepository.findByUserName(userName);
        List<Track> trackList = userObj.getTrackList();
        if(trackList.size() > 0){
            for(int i=0;i <trackList.size();i++){
                if(id.equals(trackList.get(i).getTrackId())){
                    trackList.remove(i);
                    userObj.setTrackList(trackList);
                    userTrackRepository.save(userObj);
                    break;
                }

            }
        }else{
            throw new TrackNotFoundException();
        }
        return userObj;
    }

    @Override
    public User updateCommentForTrack(String id, String comments, String userName) throws TrackNotFoundException {
        User userObj = userTrackRepository.findByUserName(userName);
        List<Track> trackList = userObj.getTrackList();
        if(trackList.size() > 0){
            for(int i=0;i <trackList.size();i++){
                if(id.equals(trackList.get(i).getTrackId())){
                    trackList.get(i).setComments(comments);
                    userObj.setTrackList(trackList);
                    userTrackRepository.save(userObj);
                    break;
                }

            }
        }else{
            throw new TrackNotFoundException();
        }
        return userObj;
    }

    @Override
    public List<Track> getTracksFromWishList(String userName) throws Exception {
        User userObj = userTrackRepository.findByUserName(userName);
        return userObj.getTrackList();
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
      UserDTO userDTO= null;
      User userObj = userTrackRepository.findByUserName(user.getUserName());
      if(userObj !=null)
            throw new UserAlreadyExistsException();
      userDTO = new UserDTO(user.getUserName(),user.getEmail(),user.getPassword());
      userTrackRepository.save(user);
      producer.sendMsdToRabbitMQ(userDTO);

      return user;
    }
}
