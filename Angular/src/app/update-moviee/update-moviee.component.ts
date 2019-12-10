import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../movie.service';
import { DatePipe } from '@angular/common';
import * as moment from 'moment'; // add this 1 of 4


@Component({
  selector: 'app-update-moviee',
  templateUrl: './update-moviee.component.html',
  styleUrls: ['./update-moviee.component.css']
})
export class UpdateMovieeComponent implements OnInit {
  title: String;
  moviee: Movie;
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute,private router: Router,
    private movieService: MovieService, private datepipe:DatePipe) {this.route.params.subscribe(params => this.title = params.title); }

  ngOnInit() {
    
    
    this.editForm = this.formBuilder.group({
      id:[],
      title: ['', Validators.required],
      director: ['', Validators.required],
      releaseDate: ['', Validators.required],
      type: ['', Validators.required],
    })
    this.movieService.getMovie(this.title).subscribe(
      data => {
        console.log('date',data.releaseDate)
        let date=new Date();
        date.setFullYear=data.releaseDate.split('/')[2];
        date.setMonth=data.releaseDate.split('/')[1];
        date.setDate=data.releaseDate.split('/')[0];
        console.log('date2',date)
        data.releaseDate=this.datepipe.transform(date, 'yyyy-MM-dd');
        this.editForm.setValue(data);

 
        })
      }
    
    
    
  onSubmit(){
   this.editForm.value['releaseDate'] = new DatePipe('en-US').transform(this.editForm.value['releaseDate'], 'dd/MM/yyyy');
    this.movieService.updateMovie(this.editForm.value).subscribe(
      data => this.router.navigate(['moviees'])
    )
  }
  
  }


