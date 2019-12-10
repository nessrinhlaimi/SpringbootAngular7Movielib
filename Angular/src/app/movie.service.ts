import { Injectable } from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from './movie';
import { environment } from '../environments/environment';




  

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8'})
  }

  private springboot = 'http://localhost:8082/api/v1/movies';


  constructor(private http: HttpClient) { }
 
 
  getMovieList(): Observable<any> {
    return this.http.get(`${this.springboot}`);
  }
  createMovie(movie): Observable<Object>  {
   
    return this.http.post
    (this.springboot +'/create' , movie,this.httpOptions);
  }
  deleteMovie(title: string): Observable<any> {
    return this.http.delete(`${this.springboot}/${title}`, { responseType: 'text' });
  }
  getMovie(title: String): Observable<any> {
    return this.http.get(`${this.springboot}/${title}`);
    
  }
  updateMovie(movie: Movie): Observable<any>{
    return this.http.put<Movie>(this.springboot+ "/" + movie.title, movie);
  }
}
