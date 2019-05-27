package com.stackroute.usertrackservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserTrackController.class)
public class UserTrackControllerTest {
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private UserTrackService userTrackService;

    private Image image;
    private Artist artist;
    private Track track;
    private List<Track> trackList;
    private User user;

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
        user = new User("Thyagu","thyagu@gmail.com","password",trackList);
    }

    @After
    public void tearDown() throws Exception {
        track = null;
        image = null;
        artist = null;
        user = null;
    }


    @Test
    public void saveTrackToWishListSuccess() throws Exception {
        when(userTrackService.saveTrackToWishList(any(),eq(user.getUserName()))).thenReturn(user);
        mockmvc.perform(post("/api/v1/usertrackservice/user/{userName}/track",user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userTrackService,times(1)).saveTrackToWishList(any(),eq(user.getUserName()));

    }

    @Test
    public void saveTrackToWishListFailure() throws Exception {
        when(userTrackService.saveTrackToWishList(any(),eq(user.getUserName()))).thenThrow(TrackAlreadyExistsException.class);
        mockmvc.perform(post("/api/v1/usertrackservice/user/{userName}/track",user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isConflict())
                .andDo(print());
        verify(userTrackService,times(1)).saveTrackToWishList(any(),eq(user.getUserName()));

    }

    @Test
    public void testRegister() throws Exception {
        when(userTrackService.registerUser(any())).thenReturn(user);
        mockmvc.perform(post("/api/v1/usertrackservice/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userTrackService,times(1)).registerUser(any());

    }



    @Test
    public void deleteTrackFromWishList() throws Exception {
        when(userTrackService.deleteTrackFromWishList(track.getTrackId(),user.getUserName())).thenReturn(user);
        mockmvc.perform(delete("/api/v1/usertrackservice/user/{userName}/track",user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userTrackService,times(1)).deleteTrackFromWishList(track.getTrackId(),user.getUserName());
    }

    @Test
    public void updateCommentsForTrack() throws Exception{
        when(userTrackService.updateCommentForTrack(track.getTrackId(),track.getComments(),user.getUserName())).thenReturn(user);
        mockmvc.perform(patch("/api/v1/usertrackservice/user/{userName}/track",user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userTrackService,times(1)).updateCommentForTrack(any(),any(),any());
    }

    @Test
    public void getTracksFromWishList() throws Exception {
        when(userTrackService.getTracksFromWishList(user.getUserName())).thenReturn(trackList);
        mockmvc.perform(get("/api/v1/usertrackservice/user/{userName}/tracks",user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userTrackService,times(1)).getTracksFromWishList(user.getUserName());
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
