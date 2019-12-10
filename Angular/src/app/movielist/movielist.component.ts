import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { Observable, Subscription } from 'rxjs';
import { MovieService } from '../movie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movielist',
  templateUrl: './movielist.component.html',
  styleUrls: ['./movielist.component.css']
})
export class MovielistComponent implements OnInit {

  moviees: Movie[] = new Array<Movie>();
  filteredMovies: Movie[] = new Array<Movie>();
  searchByTitle: string;
  searchByDirector: string;
  searchByReleaseDate: string;
  searchByType: string;


  sub :Subscription ; 
  title: string;
  moviee: Movie;
  constructor(private movieService: MovieService,
    private router: Router) { }

  ngOnInit() {
   this.sub= this.movieService.getMovieList().subscribe(
      data => {this.moviees = data ;console.log(data);});
  }
  
  deleteMovie(moviee) {
    this.movieService.deleteMovie(moviee.title)
      .subscribe(
        data => {
          console.log(data);  

        },
        error => console.log(error),()=>{
          this.moviees = this.moviees.filter(movieeList => movieeList.title !== moviee.title);        


        });
  }
  movieDetails(title: string){
    this.router.navigate(['details', title]);
  }
  updateMovie(moviee: Movie){
    this.router.navigate(['update/'  +moviee.title.toString()]);
  }
  get searchTermByTitle() : string {
    return this.searchByTitle ;
  }
  set searchTermByTitle(value: string){
    this.searchByTitle = value;
    this.moviees = this.moviees.filter(movie => movie.title.toLowerCase().indexOf(value.toLowerCase()) !== -1);
  }
  get searchTermByDirector() : string {
    return this.searchByDirector ;
  }
  set searchTermByDirector(value: string){
    this.searchByDirector = value;
    this.moviees = this.moviees.filter(movie => movie.director.toLowerCase().indexOf(value.toLowerCase()) !== -1);
  }
  get searchTermByReleaseDate() : string {
    return this.searchByReleaseDate ;
  }
  set searchTermByReleaseDate(value: string){
    this.searchByReleaseDate = value;
    this.moviees = this.moviees.filter(movie => movie.releaseDate.toString().toLowerCase().indexOf(value.toLowerCase()) !== -1);
  }
  get searchTermByType() : string {
    return this.searchByType ;
  }
  set searchTermByType(value: string){
    this.searchByType = value;
    this.moviees = this.moviees.filter(movie => movie.type.toLowerCase().indexOf(value.toLowerCase()) !== -1);
  }
}
