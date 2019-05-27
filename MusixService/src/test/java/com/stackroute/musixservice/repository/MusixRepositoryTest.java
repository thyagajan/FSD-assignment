package com.stackroute.musixservice.repository;

import com.stackroute.musixservice.domain.Artist;
import com.stackroute.musixservice.domain.Image;
import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.service.MusixService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MusixRepositoryTest {

    @Autowired
    private MusixRepository musixRepository;

    private Image image;
    private Artist artist;
    private Track track;

    @Before
    public void setUp(){
      image  = new Image(1,"url","large");
      artist = new Artist(101,"John","url",image);
      track=new Track("track561","my nee track","my new comments","123","url",artist);

    }
    @After
    public void tearDown(){
        image = null;
        artist = null;
        track=null;
        musixRepository.deleteAll();
    }

    @Test
    public void saveTrackTest(){
        musixRepository.insert(track);
        Track fetchTrack = musixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(fetchTrack.getTrackName(),track.getTrackName());

    }

    @Test
    public void updateCommentsTest(){
        musixRepository.insert(track);
        Track fetchTrack = musixRepository.findById(track.getTrackId()).get();
        fetchTrack.setComments("Test Comment");
        musixRepository.save(fetchTrack);
        Track commentTrack = musixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("Test Comment",commentTrack.getComments());
    }

    @Test
    public void deleteTrackTest(){
        musixRepository.insert(track);
        Track fetchTrack = musixRepository.findById(track.getTrackId()).get();
        musixRepository.delete(fetchTrack);
        Assert.assertEquals(Optional.empty(), musixRepository.findById(track.getTrackId()));
    }

    @Test
    public void getAllTest(){
        musixRepository.insert(track);
        image  = new Image(1,"url","large");
        artist = new Artist(101,"John","url",image);
        track = new Track("Track 123","New Track","Change Comments","145","url1",artist);
        musixRepository.insert(track);
        List<Track> tracks = musixRepository.findAll();
        Assert.assertEquals(tracks.size(),2);
        Assert.assertEquals("145",tracks.get(1).getTrackListners());


    }


}
