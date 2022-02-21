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

import com.prajina.academy.TestData;
import com.prajina.academy.entity.ModuleEntity;
import com.prajina.academy.model.Module;
import com.prajina.academy.repository.ModuleRepository;
import com.prajina.academy.transformer.ModuleMapper;

@ActiveProfiles("test")
@SpringBootTest(classes = { ModuleService.class, ModuleServiceImpl.class, ModuleRepository.class, ModuleMapper.class, AcademyMocks.class })
public class ModuleServiceImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ModuleService service;

	@Autowired
	private ModuleRepository mockedRepository;

	private TestData testData = new TestData();
	
	@Test
	public void testFindAll_valid_input() {
		doReturn(testData.mockOnePageModuleData()).when(mockedRepository).findAll(any(Pageable.class));

	    List<Module> modules = service.findAll(0, 10, "id");

		assertNotNull(modules);
		assertEquals(modules.size(), 3);
		assertEquals(modules.get(0).getName(), "Java SE 7");
		assertEquals(modules.get(1).getName(), "Java Modifiers");
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
		doReturn(testData.mockOnePageModuleData()).when(mockedRepository).findAll(any(Pageable.class));

		List<Module> modules = service.findAll(0, 3, "name");
		assertNotNull(modules);
		assertEquals(modules.size(), 3);
		assertEquals(modules.get(0).getName(), "Java SE 7");
		assertEquals(modules.get(1).getName(), "Java Modifiers");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "Invalid input!")

	public void testFindAll_nullSortBy_exception() {
		service.findAll(0, 6, null);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No module provided to save!")

	public void testCreate_nullInput_exception() {
		service.create(null);
	}
	
	@Test(expectedExceptions = {Exception.class})

	public void testCreate_emptyModule_exception() {
		service.create(new Module());
	}
	
	@Test
	public void testCreate_validInput_returnModule() {
		doReturn(testData.getModuleEntity()).when(mockedRepository).save(any());
		
		Module module = service.create(testData.getModule());
		
		assertNotNull(module);
		assertEquals(module.getId(), 1L);
		assertEquals(module.getName(), "Java Modifiers");
		assertEquals(module.getStatus(), "active");
		assertNotEquals(module.getId(), 2L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No module id provided to update!")

	public void testUpdate_nullInput_exception() {
		service.update(null, null);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No module provided to update!")

	public void testUpdate_nullModule_exception() {
		service.update(1L, null);
	}
	
	@Test(expectedExceptions = {Exception.class})

	public void testUpdate_emptyModule_exception() {
		service.update(1L, new Module());
	}
	
	@Test//(expectedExceptions = {Exception.class})

	public void testUpdate_validInput_returnUpdatedModule() {
		ModuleEntity moduleEntity = testData.getModuleEntity();
		ModuleEntity updatedModuleEntity = new ModuleEntity();
		updatedModuleEntity = testData.getModuleEntity();
		updatedModuleEntity.setName(moduleEntity.getName() + " Updated");
		
		when(mockedRepository.save(any())).thenReturn(moduleEntity, updatedModuleEntity);
		when(mockedRepository.findById(any())).thenReturn(Optional.of(moduleEntity));
	
		Module createdModule = service.create(testData.getModule());
		createdModule.setName(createdModule.getName() + " Updated");
		
		Module updatedModule = service.update(1L, createdModule);
		assertNotNull(updatedModule);
		assertEquals(updatedModule.getId(), 1L);
		assertEquals(updatedModule.getName(), "Java Modifiers Updated");
		assertNotEquals(updatedModule.getName(), "Java Modifiers");
		
		verify(mockedRepository).save(moduleEntity);
	}
	
	@Test
	public void testDelete_validInput_success() {
		doNothing().when(mockedRepository).deleteById(anyLong());

		service.deleteById(1L);
		verify(mockedRepository).deleteById(1L);
	}
}
