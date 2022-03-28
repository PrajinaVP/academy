import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseModuleDialogComponent } from './course-module-dialog.component';

describe('CourseModuleDialogComponent', () => {
  let component: CourseModuleDialogComponent;
  let fixture: ComponentFixture<CourseModuleDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseModuleDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseModuleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
