import { TestBed } from '@angular/core/testing';

import { MusixService } from './musix.service';
import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing'
import { Track } from 'src/app/modules/musix/track';


describe('MusixService', () => {
  let track = new Track();
  track = {
    artist: {
      artistId: 0,
      image: {
        imageId: 0,
        size: "string",
        url: "string"
      },
      name: "string",
      url: "string"
    },
    comments: "string",
    listeners : "string",
    name: "string",
    trackId: "string1",
    url: "string"
  };
  const springApi = "http://localhost:9005/usertrackservice/api/v1/usertrackservice/user/";
  let musixService :MusixService;
  let httpTestingController:HttpTestingController;
  beforeEach(() => 
    {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[MusixService]
    });
    musixService = TestBed.get(MusixService);
    httpTestingController= TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    //const service: MusixService = TestBed.get(MusixService);
    expect(musixService).toBeTruthy();
  });

  it('should add the track to wishlist', () =>{
    musixService.addToWishList(track).subscribe(result =>{
      expect(result.body).toBe(track);
    });
    const url = springApi+"test/track";
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('POST');
  });

  it('should get all tracks from wishlist', () =>{
    musixService.getAllTracksFromWishList().subscribe(result =>{
      
    });
    const url = springApi+"test/tracks";
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('GET');
  });

  it('should delete the track from wishlist', () =>{
    musixService.deleteTrackFromWishList(track).subscribe(result =>{
      //expect(result.body).toBe(track);
    });
    const url = springApi+"test/track";
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('DELETE');
  });

  it('should update comments for the track from wishlist', () =>{
    musixService.updateComments(track).subscribe(result =>{
      //expect(result.body).toBe(track);
    });
    const url = springApi+"test/track";
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('PATCH');
  });

});
