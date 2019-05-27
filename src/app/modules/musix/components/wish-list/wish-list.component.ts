import { Component, OnInit } from '@angular/core';
import { MusixService } from 'src/app/modules/musix/musix.service';
import { Track } from 'src/app/modules/musix/track';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {

  tracks:Array<Track>;
  isWishListPage:boolean = true;
  constructor(private musixService:MusixService,
  private matSnackBar:MatSnackBar) { }


  deleteFromWishList(track){
      this.musixService.deleteTrackFromWishList(track).subscribe(
        data =>{
          const index = this.tracks.indexOf(track);
          this.tracks.splice(index,1);
          this.matSnackBar.open("Deleted Successfully"," ",{duration:1000});
        }
      );
      return this.tracks;
  }

  updateComments(track){
    this.musixService.updateComments(track).subscribe(
      data =>{
        console.log('data ',data);
        this.matSnackBar.open('Successuflly Updated'," ",{duration:1000});
      }
    );
    return this.tracks;
}

  ngOnInit() {
    this.musixService.getAllTracksFromWishList().subscribe(data =>{
      const message = " WishList is empty";
      this.tracks = data;
      if(data.length === 0){
        this.matSnackBar.open(message," ",{duration:1000});
      }
    });

    
  }

}
