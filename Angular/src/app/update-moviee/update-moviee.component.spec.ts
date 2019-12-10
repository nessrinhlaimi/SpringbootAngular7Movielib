import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMovieeComponent } from './update-moviee.component';

describe('UpdateMovieeComponent', () => {
  let component: UpdateMovieeComponent;
  let fixture: ComponentFixture<UpdateMovieeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateMovieeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMovieeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
