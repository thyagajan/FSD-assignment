package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.config.Producer;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import com.stackroute.usertrackservice.repository.UserTrackRepositoryTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserTrackServiceTest {

    @Mock
    private UserTrackRepository musixRepository;

    @Mock
    private Producer producer;

    private Track track;
    private Image image;
    private Artist artist;
    private User user;

    private List<Track> trackList;

    @InjectMocks
    private UserTrackServiceImpl musixServiceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        image  = new Image(1,"url","large");
        artist = new Artist(101,"John","url",image);
        track=new Track("track5","my nee track","my new comments","123","url",artist);
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        user = new User("Thyagu1","thyagu@gmail.com","password",trackList);

    }

    @After
    public void tearDown(){
        musixRepository.deleteAll();
        track = null;
        artist = null;
        image = null;
        user = null;
    }

    @Test
    public void testSaveTrackSucess() throws TrackAlreadyExistsException {
        user = new User("testUser","test@gmail.com","password",null);
        when(musixRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = musixServiceImpl.saveTrackToWishList(track,user.getUserName());
        Assert.assertEquals(user,fetchedUser);
        verify(musixRepository,times(1)).save(user);

    }


    @Test
    public void testUpdateComments() throws TrackNotFoundException {
        when(musixRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = musixServiceImpl.updateCommentForTrack(track.getTrackId(),"New Comments today",user.getUserName());
        Assert.assertEquals("New Comments today",fetchedUser.getTrackList().get(0).getComments());
        verify(musixRepository,times(1)).save(user);
        verify(musixRepository,times(1)).findByUserName(user.getUserName());

    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException{
        when(musixRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = musixServiceImpl.deleteTrackFromWishList(track.getTrackId(),user.getUserName());
        Assert.assertEquals(user,fetchedUser);
        verify(musixRepository,times(1)).save(user);
        verify(musixRepository,times(1)).findByUserName(user.getUserName());

    }

    @Test
    public void testGetAllTracks() throws Exception{
        when(musixRepository.findByUserName(user.getUserName())).thenReturn(user);
        List<Track> trackList = musixServiceImpl.getTracksFromWishList(user.getUserName());
        Assert.assertEquals(trackList,user.getTrackList());
        verify(musixRepository,times(1)).findByUserName(user.getUserName());

    }

}
