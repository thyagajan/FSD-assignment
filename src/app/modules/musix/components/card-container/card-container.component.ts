import { Component, OnInit } from '@angular/core';
import { Track } from 'src/app/modules/musix/track';
import { Artist } from 'src/app/modules/musix/artist';
import { Image } from 'src/app/modules/musix/image';
import {MusixService} from 'src/app/modules/musix/musix.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {

  tracks : Array<Track>;
  searchTracks : Array<Track>;
  trackObj :Track;
  artistObj : Artist;
  imageObj : Image;
  countryName : string;
  id:number;
  artistName :string;
  
  constructor(private musixService:MusixService,
    private routes:ActivatedRoute,
    private matSnackBar:MatSnackBar) {
    this.tracks = [];
   }

  ngOnInit() {
    const tempData = this.routes.data.subscribe(newData =>{
      this.countryName = newData.country;
      console.log("country", this.countryName);
    });

    this.id = 0;
    
    this.musixService.gettrackDetails(this.countryName).subscribe(tracks =>{
      console.log(tracks);
      this.tracks = [];
      const data = tracks['tracks']['track'];
      data.forEach(targetData => {
        this.id++;
        this.trackObj = targetData;
        this.artistObj = targetData['artist'];
        this.imageObj = new Image();
        this.imageObj.url = targetData['image'][2]['#text'];
        this.imageObj.size = targetData['image'][2]['size'];
        this.trackObj.artist = this.artistObj;
        this.artistObj.image = this.imageObj;
        this.trackObj.trackId = this.countryName.slice(0,3)+this.id;
        this.tracks.push(this.trackObj);
        this.searchTracks = this.tracks;
        
      });
      
    });
  }

  addToWishList(track){
    let statusCode;
    console.log("Card container component ",track);
    this.musixService.addToWishList(track).subscribe(
      data =>{
        console.log("add to wish list response ", data);
        statusCode = data.status;
        if(statusCode === 201){
          console.log("add to wish list response ", data);
          this.matSnackBar.open("Track successfully Added"," ",{duration:1000});
        }
      },
      error=>{
        
        statusCode = error.status;
        console.log("Addtowishlist failed ",statusCode);
        if(statusCode == 409){
          this.matSnackBar.open(error.error.message," ",{duration:1000});
        }
      }
    );
  }

  onKey(event:any){
    this.artistName = event.target.value;
    console.log("artist name",this.artistName);
    const result = this.searchTracks.filter(track =>{
      return track.artist.name.match(this.artistName);
    });
    this.tracks = result;
  }
}
