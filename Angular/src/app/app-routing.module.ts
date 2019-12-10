

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovielistComponent } from './movielist/movielist.component';
import { MovieComponent } from './movie/movie.component';
import { UpdateMovieeComponent } from './update-moviee/update-moviee.component';


const routes: Routes = [
  { path: '', redirectTo: 'moviee', pathMatch: 'full' },
  { path: 'moviees', component: MovielistComponent },
  { path: 'add', component: MovieComponent },
  { path: 'update/:title', component: UpdateMovieeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
