package com.prajina.academy.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.prajina.academy.data.TestData;
import com.prajina.academy.entity.CourseEntity;
import com.prajina.academy.model.Course;
import com.prajina.academy.repository.CourseRepository;
import com.prajina.academy.transformer.CourseMapper;
import com.prajina.academy.transformer.ModuleMapper;

@ActiveProfiles("test")
@SpringBootTest(classes = { CourseService.class, CourseServiceImpl.class, CourseRepository.class, CourseMapper.class, ModuleMapper.class, AcademyMocks.class })
public class CourseServiceImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CourseService service;

	@Autowired
	private CourseRepository mockedRepository;

//	@Autowired
//	private CourseMapper mockCourseMapper;
	
	private TestData testData = new TestData();
	
	@Test
	public void testFindAll_valid_input() {
		doReturn(testData.mockOnePageCourseData()).when(mockedRepository).findAll(any(Pageable.class));

		/*
		 * when(mockCourseMapper.toModel(any(ArrayList.class)))
		 * .thenReturn(mockCourseEntityList());
		 */
		 
	    List<Course> courses = service.findAll(0, 10, "id");

		assertNotNull(courses);
		assertEquals(courses.size(), 3);
		assertEquals(courses.get(0).getName(), "Java SE 7");
		assertEquals(courses.get(1).getName(), "Java SE 17");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_nullPageSize_exception() {
		service.findAll(0, null, "name");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_negativePageSize_exception() {
		service.findAll(0, -3, "name");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_zeroPageSize_exception() {
		service.findAll(0, 0, "name");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_nullPageNumber_exception() {
		service.findAll(null, 3, "name");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_negativePageNumber_exception() {
		service.findAll(null, 3, "name");
	}
	
	@Test
	public void testFindAll_zeroPageNumber_returnsList() {
		doReturn(testData.mockOnePageCourseData()).when(mockedRepository).findAll(any(Pageable.class));

		List<Course> courses = service.findAll(0, 3, "name");
		assertNotNull(courses);
		assertEquals(courses.size(), 3);
		assertEquals(courses.get(0).getName(), "Java SE 7");
		assertEquals(courses.get(1).getName(), "Java SE 17");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_nullSortBy_exception() {
		service.findAll(0, 6, null);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course provided to save!")

	public void testCreate_nullInput_exception() {
		service.create(null);
	}
	
	@Test(expectedExceptions = {Exception.class})

	public void testCreate_emptyCourse_exception() {
		service.create(new Course());
	}
	
	@Test
	public void testCreate_validInput_returnCourse() {
		doReturn(testData.getCourseEntity()).when(mockedRepository).save(any());
		
		Course course = service.create(testData.getCourse());
		
		assertNotNull(course);
		assertEquals(course.getId(), 1L);
		assertEquals(course.getName(), "Java SE 17");
		assertEquals(course.getStatus(), "active");
		assertNotEquals(course.getId(), 2L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course id provided to update!")

	public void testUpdate_nullInput_exception() {
		service.update(null, null);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course provided to update!")

	public void testUpdate_nullCourse_exception() {
		service.update(1L, null);
	}
	
	@Test(expectedExceptions = {Exception.class})

	public void testUpdate_emptyCourse_exception() {
		service.update(1L, new Course());
	}
	
	@Test

	public void testUpdate_validInput_returnUpdatedCourse() {
		CourseEntity courseEntity = testData.getCourseEntity();
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedRepository.findById(any())).thenReturn(Optional.of(courseEntity));
	
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		Course updatedCourse = service.update(1L, createdCourse);
		assertNotNull(updatedCourse);
		assertEquals(updatedCourse.getId(), 1L);
		assertEquals(updatedCourse.getName(), "Java SE 17 Updated");
		assertNotEquals(updatedCourse.getName(), "Java SE 17");
		
		verify(mockedRepository).save(courseEntity);
	}
	
	@Test
	public void testDelete_validInput_success() {
		doNothing().when(mockedRepository).deleteById(anyLong());

		service.deleteById(1L);
		verify(mockedRepository).deleteById(1L);
	}

}
