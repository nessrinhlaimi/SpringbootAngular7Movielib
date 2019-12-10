import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { Movie } from '../movie';
import { MovieService } from '../movie.service';
import { Title } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {
  movie: Movie = new Movie();
  submitted = false;
  moviees: Observable<Movie[]>;

   @ViewChild('template') private template;
  constructor(private fromBuilder: FormBuilder,private movieService: MovieService,private router: Router) { }
  
  addForm: FormGroup;

  ngOnInit() {
  
  
  this.addForm = this.fromBuilder.group({
    title: ['', Validators.required],
    director: ['', Validators.required],
    releaseDate: ['', Validators.required],
    type: ['', Validators.required],
  })
}

  newMovie(): void {
    this.submitted = false;
    this.movie = new Movie();
    
  }
  
  onSubmit(){
    this.addForm.value['releaseDate'] = new DatePipe('en-US').transform(this.addForm.value['releaseDate'], 'dd/MM/yyyy');
    this.movieService.createMovie(this.addForm.value).subscribe(
      data => this.router.navigate(['moviees'])
    
      
    
    )
   
   
}
}