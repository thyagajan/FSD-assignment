import { Artist } from 'src/app/modules/musix/artist';

export class Track{
    trackId : string;
    name    : string;
    listeners:string;
    comments :string;
    url :string;
    artist : Artist;
}