import { TestBed } from '@angular/core/testing';

import { CourseModuleService } from './course-module.service';

describe('CourseModuleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CourseModuleService = TestBed.get(CourseModuleService);
    expect(service).toBeTruthy();
  });
});
