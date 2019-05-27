package com.stackroute.musixservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.musixservice.domain.Artist;
import com.stackroute.musixservice.domain.Image;
import com.stackroute.musixservice.domain.Track;
import com.stackroute.musixservice.exception.TrackAlreadyExistsException;
import com.stackroute.musixservice.service.MusixService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MusixServiceController.class)
public class MusixServiceControllerTest {
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private MusixService musicService;

    private Image image;
    private Artist artist;
    private Track track;
    private List<Track> trackList;

    @Before
    public void setUp() throws Exception {
        image  = new Image(1,"url","large");
        artist = new Artist(101,"John","url",image);
        track=new Track("track561","my nee track","my new comments","123","url",artist);
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        image  = new Image(2,"url2","Small");
        artist = new Artist(102,"Jacob","url2",image);
        track=new Track("track562","new track","my new comments2","132","url2",artist);
        trackList.add(track);
    }

    @After
    public void tearDown() throws Exception {
        track = null;
        image = null;
        artist = null;
    }

    @Test
    public void saveTrackToWishListSuccess() throws Exception {
        when(musicService.saveTrackToWishList(any())).thenReturn(track);
        mockmvc.perform(post("/api/v1/musixservice/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(musicService,times(1)).saveTrackToWishList(any());

    }

    @Test
    public void saveTrackToWishListFailure() throws Exception {
        when(musicService.saveTrackToWishList(any())).thenThrow(TrackAlreadyExistsException.class);
        mockmvc.perform(post("/api/v1/musixservice/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isConflict())
                .andDo(print());
        verify(musicService,times(1)).saveTrackToWishList(any());

    }

    @Test
    public void deleteTrackFromWishList() throws Exception {
        when(musicService.deleteTrackFromWishList(track.getTrackId())).thenReturn(true);
        mockmvc.perform(delete("/api/v1/musixservice/track/Track2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(musicService,times(1)).deleteTrackFromWishList(any());
    }

    @Test
    public void updateCommentsForTrack() throws Exception{
        when(musicService.updateCommentForTrack(track.getTrackId(),track.getComments())).thenReturn(track);
        mockmvc.perform(put("/api/v1/musixservice/track/Track2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(musicService,times(1)).updateCommentForTrack(any(),any());
    }

    @Test
    public void getTracksFromWishList() throws Exception {
        when(musicService.getTracksFromWishList()).thenReturn(trackList);
        mockmvc.perform(get("/api/v1/musixservice/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(musicService,times(1)).getTracksFromWishList();
    }

    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result ;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(obj);

        }catch(Exception e){
            result = "Error Processing JSON";
        }
        return result;
    }
}