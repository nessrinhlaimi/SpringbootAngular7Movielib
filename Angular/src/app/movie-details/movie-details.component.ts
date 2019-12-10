import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../movie';
import { MovieService } from '../movie.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  title: string;
  moviee: Movie;

  constructor(private route: ActivatedRoute,private router: Router,
    private movieService: MovieService) { }

  ngOnInit() {
    
  
 
};
}
