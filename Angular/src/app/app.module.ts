import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { MovieComponent } from './movie/movie.component';
import { MovielistComponent } from './movielist/movielist.component';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { MovieService } from './movie.service';
import { UpdateMovieeComponent } from './update-moviee/update-moviee.component';
import { DatePipe } from '@angular/common';
@NgModule({
  declarations: [
    AppComponent,
    
    MovieComponent,
    MovielistComponent,
    MovieDetailsComponent,
    UpdateMovieeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  exports: [HttpClientModule],
  providers: [
  MovieService,
  DatePipe
 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
