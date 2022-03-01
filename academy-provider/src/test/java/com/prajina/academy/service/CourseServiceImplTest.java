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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.prajina.academy.data.TestData;
import com.prajina.academy.entity.CourseEntity;
import com.prajina.academy.entity.ModuleEntity;
import com.prajina.academy.model.Course;
import com.prajina.academy.repository.CourseRepository;
import com.prajina.academy.repository.ModuleRepository;
import com.prajina.academy.transformer.CourseMapper;
import com.prajina.academy.transformer.ModuleMapper;

@ActiveProfiles("test")
@SpringBootTest(classes = { CourseService.class, CourseServiceImpl.class, CourseRepository.class, CourseMapper.class, ModuleRepository.class,ModuleMapper.class, AcademyMocks.class })
public class CourseServiceImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CourseService service;

	@Autowired
	private CourseRepository mockedCourseRepository;
	
	@Autowired
	private ModuleRepository mockedModuleRepository;
	
	private TestData testData = new TestData();
	
	@Test
	public void testFindAll_valid_input() {
		doReturn(testData.mockOnePageCourseData()).when(mockedCourseRepository).findAll(any(Pageable.class));
		 
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
		doReturn(testData.mockOnePageCourseData()).when(mockedCourseRepository).findAll(any(Pageable.class));

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
	
	@Test
	public void testCreate_validInput_returnCourse() {
		doReturn(testData.getCourseEntity()).when(mockedCourseRepository).save(any());
		
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
	
	@Test

	public void testUpdate_validInput_returnUpdatedCourse() {
		CourseEntity courseEntity = testData.getCourseEntity();
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(Optional.of(courseEntity));
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		Course updatedCourse = service.update(1L, createdCourse);
		assertNotNull(updatedCourse);
		assertEquals(updatedCourse.getId(), 1L);
		assertEquals(updatedCourse.getName(), "Java SE 17 Updated");
		assertNotEquals(updatedCourse.getName(), "Java SE 17");
		
		verify(mockedCourseRepository).save(courseEntity);
	}
	

	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Course with id 1 not found!")
	public void testUpdate_courseNotInDB_throwsException() {
		CourseEntity courseEntity = testData.getCourseEntity();
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(null);
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		service.update(1L, createdCourse);
	}
	
	@Test

	public void testAddModule_validInput_returnUpdatedCourse() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(Optional.of(courseEntity));
		when(mockedModuleRepository.findById(any())).thenReturn(Optional.of(moduleEntity));
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		Course updatedCourse = service.addModule(1L, createdCourse.getId());
		assertNotNull(updatedCourse);
		assertEquals(updatedCourse.getId(), 1L);
		assertEquals(updatedCourse.getName(), "Java SE 17 Updated");
		assertNotEquals(updatedCourse.getName(), "Java SE 17");
		
		verify(mockedCourseRepository).save(courseEntity);
	}
	
	@Test

	public void testAddModule_validInputCourseCreatedWithoutModule_returnUpdatedCourse() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		courseEntity.setModules(null);
		courseEntity.setName("Java SE 17 Updated");
		
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(Optional.of(courseEntity));
		when(mockedModuleRepository.findById(any())).thenReturn(Optional.of(moduleEntity));
		
		Course updatedCourse = service.addModule(1L, courseEntity.getId());
		assertNotNull(updatedCourse);
		assertEquals(updatedCourse.getId(), 1L);
		assertEquals(updatedCourse.getName(), "Java SE 17 Updated");
		
		verify(mockedCourseRepository).save(courseEntity);
	}

	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Course with id 1 not found!")
	public void testAddModule_CourseNotInDB_throwsException() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		courseEntity.setModules(null);
		
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(null);
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		service.addModule(1L, createdCourse.getId());
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Module with id 1 not found!")
	public void testAddModule_ModuleNotInDB_throwsException() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		courseEntity.setModules(null);
		
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(Optional.of(courseEntity));
		when(mockedModuleRepository.findById(any())).thenReturn(null);
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		service.addModule(1L, createdCourse.getId());
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course id provided to update!")

	public void testAddModule_nullCourseIdInput_exception() {
		service.addModule(null, 1L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No module provided to update!")

	public void testAddModule_nullModuleIdInput_exception() {
		service.addModule(1L, null);
	}
	
	@Test

	public void testRemoveModule_validInput_returnUpdatedCourse() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity, courseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(Optional.of(courseEntity));
		when(mockedModuleRepository.findById(any())).thenReturn(Optional.of(moduleEntity));
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		Course updatedCourse = service.addModule(1L, createdCourse.getId());
		
		Course updatedCourseModule = service.removeModule(updatedCourse.getId(), moduleEntity.getId());
		assertNotNull(updatedCourseModule);
		assertEquals(updatedCourseModule.getModules().size(), 0);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Course with id 1 not found!")
	public void testRemoveModule_CourseNotInDB_throwsException() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		Set<ModuleEntity> moduleSet = new HashSet<ModuleEntity>();
		moduleSet.add(moduleEntity);
		
		CourseEntity courseEntity = testData.getCourseEntity();
		courseEntity.setModules(null);
		
		CourseEntity updatedCourseEntity = new CourseEntity();
		updatedCourseEntity = testData.getCourseEntity();
		updatedCourseEntity.setModules(moduleSet);
		updatedCourseEntity.setName(courseEntity.getName() + " Updated");
		
		when(mockedCourseRepository.save(any())).thenReturn(courseEntity, updatedCourseEntity);
		when(mockedCourseRepository.findById(any())).thenReturn(null);
		
		Course createdCourse = service.create(testData.getCourse());
		createdCourse.setName(createdCourse.getName() + " Updated");
		
		service.removeModule(1L, createdCourse.getId());
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course id provided to update!")

	public void testRemoveModule_nullCourseIdInput_exception() {
		service.removeModule(null, 1L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No module id provided to update!")

	public void testRemoveModule_nullModuleIdInput_exception() {
		service.removeModule(1L, null);
	}
	
	@Test
	public void testDelete_validInput_success() {
		doNothing().when(mockedCourseRepository).deleteById(anyLong());

		service.deleteById(1L);
		verify(mockedCourseRepository).deleteById(1L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No course id provided to delete!")

	public void testDelete_nullInput_exception() {
		service.deleteById(null);
	}

}