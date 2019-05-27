package com.stackroute.usertrackservice.repository;

import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserTrackRepositoryTest {

    @Autowired
    private UserTrackRepository musixRepository;

    private Image image;
    private Artist artist;
    private Track track;
    private User user;

    @Before
    public void setUp(){
      image  = new Image(1,"url","large");
      artist = new Artist(101,"John","url",image);
      track=new Track("track561","my nee track","my new comments","123","url",artist);
      List<Track> trackList = new ArrayList<>();
      trackList.add(track);
      user = new User("Thyagu","thyagu@gmail.com","password",trackList);
    }

    @After
    public void tearDown(){
        image = null;
        artist = null;
        track=null;
        user = null;
        musixRepository.deleteAll();
    }

    @Test
    public void saveTrackTest(){
        musixRepository.save(user);
        User fetchUser = musixRepository.findByUserName(user.getUserName());
        List<Track> dbTrackList = fetchUser.getTrackList();
        Assert.assertEquals(dbTrackList.get(0).getTrackId(),user.getTrackList().get(0).getTrackId());

    }

    @Test
    public void updateCommentsTest(){
        musixRepository.save(user);
        User fetchUser = musixRepository.findByUserName(user.getUserName());
        List<Track> dbTrackList = fetchUser.getTrackList();
        dbTrackList.get(0).setComments("Test Comment123");
        fetchUser.setTrackList(dbTrackList);
        musixRepository.save(fetchUser);
        User commentUser = musixRepository.findByUserName(user.getUserName());
        Assert.assertEquals("Test Comment123",commentUser.getTrackList().get(0).getComments());
    }


}
