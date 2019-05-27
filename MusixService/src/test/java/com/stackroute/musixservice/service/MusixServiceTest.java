package com.stackroute.musixservice.service;

import com.stackroute.musixservice.domain.Artist;
import com.stackroute.musixservice.domain.Image;
import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.exception.TrackAlreadyExistsException;
import com.stackroute.musixservice.exception.TrackNotFoundException;
import com.stackroute.musixservice.repository.MusixRepository;
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

public class MusixServiceTest {

    @Mock
    private MusixRepository musixRepository;

    private Track track;
    private Optional optional;
    private Image image;
    private Artist artist;

    private List<Track> trackList;

    @InjectMocks
    private MusixServiceImpl musixServiceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        image  = new Image(1,"url","large");
        artist = new Artist(101,"John","url",image);
        track=new Track("track561","my nee track","my new comments","123","url",artist);
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        optional = Optional.of(track);

    }

    @After
    public void tearDown(){
        track = null;
        artist = null;
        image = null;
    }

    @Test
    public void testSaveTrackSucess() throws TrackAlreadyExistsException {
        when(musixRepository.insert(track)).thenReturn(track);
        Track fetchTrack = musixServiceImpl.saveTrackToWishList(track);
        Assert.assertEquals(track,fetchTrack);
        verify(musixRepository,times(1)).insert(track);
        verify(musixRepository,times(1)).findById(track.getTrackId());

    }

    @Test(expected=TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException {
        when(musixRepository.insert(track)).thenReturn(track);
        when(musixRepository.findById(track.getTrackId())).thenReturn(optional);
        System.out.println("optional present :"+optional.isPresent());
        Track fetchTrack = musixServiceImpl.saveTrackToWishList(track);
        verify(musixRepository,times(1)).insert(track);
        verify(musixRepository,times(1)).findById(track.getTrackId());
    }

    @Test
    public void testUpdateComments() throws TrackNotFoundException {
        when(musixRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setComments("Comments updated");
        Track fetchTrack = musixServiceImpl.updateCommentForTrack(track.getTrackId(),track.getComments());
        System.out.println("fetch Track :"+fetchTrack);
        Assert.assertEquals(fetchTrack.getComments(),track.getComments());
        verify(musixRepository,times(1)).save(track);
        verify(musixRepository,times(1)).findById(track.getTrackId());

    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException{
        when(musixRepository.findById(track.getTrackId())).thenReturn(optional);
        boolean hasDeleted = musixServiceImpl.deleteTrackFromWishList(track.getTrackId());
        Assert.assertEquals(true,hasDeleted);
        verify(musixRepository,times(1)).deleteById(track.getTrackId());
        verify(musixRepository,times(1)).findById(track.getTrackId());


    }

    @Test
    public void testGetAllTracks() throws Exception{
        when(musixRepository.findAll()).thenReturn(trackList);
        List<Track> tracks = musixServiceImpl.getTracksFromWishList();
        Assert.assertEquals(tracks,trackList);
        verify(musixRepository,times(1)).findAll();

    }
}
