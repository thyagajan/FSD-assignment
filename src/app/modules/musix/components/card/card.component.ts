import { Component, OnInit,Input, Output,EventEmitter} from '@angular/core';
import { Track } from 'src/app/modules/musix/track';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { DialogComponent } from 'src/app/modules/musix/components/dialog/dialog.component';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  track : Track;

  @Input()
  isWishListPage:boolean;

  @Output()
  addToWishList = new EventEmitter();

  @Output()
  deleteFromWishList = new EventEmitter();

  @Output()
  updateComments = new EventEmitter();

  constructor(private dialog:MatDialog) { }

  emitAddWishListEvent(track){
    console.log("Emitting track from card component ",track);
    this.addToWishList.emit(track);
  }

  emitDeleteWishListEvent(track){
    console.log("Emitting track from card component ",track);
    this.deleteFromWishList.emit(track);
  }

  updateComment(){
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: this.track.comments
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed',result);
      this.track.comments = result;
      this.updateComments.emit(this.track);
    });
  }

  
  ngOnInit() {
  }

}
