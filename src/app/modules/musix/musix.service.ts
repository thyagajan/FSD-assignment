import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Track } from 'src/app/modules/musix/track';
import  {USER_NAME} from 'src/app/modules/authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class MusixService {

  thirdPartyApi : string;
  apiKey : string;
  springApi : string;
  userName : string;
  constructor(private httpClient: HttpClient) {
    this.thirdPartyApi = 'http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=';
    this.apiKey = '&api_key=4c8694c3de8090c92247787b9579baeb&format=json';
    this.springApi = "http://localhost:9005/usertrackservice/api/v1/usertrackservice/user/";
    this.userName = sessionStorage.getItem(USER_NAME);
   }

  gettrackDetails(countryName:string):Observable<any>{
    const url = this.thirdPartyApi+countryName+this.apiKey;
    return this.httpClient.get(url);
  }

  addToWishList(track:Track){
    
    const url = this.springApi+this.userName+'/track';
    return this.httpClient.post(url,track,{
      observe:"response"
    });

  }

  getAllTracksFromWishList():Observable<Track[]>{
    const url = this.springApi+this.userName+'/tracks';
    return this.httpClient.get<Track[]>(url);
  }

  deleteTrackFromWishList(track:Track){
    const url = this.springApi+this.userName+'/track';
    const options = {
      headers :new HttpHeaders({
        'Content-Type' : 'application/json'
      }),
      body :track
    };
    return this.httpClient.delete(url,options);
  }

  updateComments(track:Track){
    const url = this.springApi+this.userName+'/track';
    return this.httpClient.patch(url,track,{
      observe:"response"
    })
  }
}
