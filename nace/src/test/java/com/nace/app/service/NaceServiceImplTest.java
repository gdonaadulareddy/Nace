package com.nace.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nace.app.dao.NaceRepository;
import com.nace.app.exception.NaceNotFoundException;
import com.nace.app.model.Nace;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class NaceServiceImplTest {
	
	@Mock
	private NaceRepository repo;
	
	@InjectMocks
	private NaceServiceImpl impl;
	
	Nace entity = null;
	
	@BeforeAll
	void setUp() {
		repo = Mockito.mock(NaceRepository.class);
		entity = new Nace(398482L, 1L, "1", "A",
				"Crop and animal production, hunting and related service activities",
				"This division includes two basic activities, namely the production of crop products and production of animal products"
						+ ", covering also the forms of organic agriculture, the growing of genetically modified crops and the raising"
						+ " of genetically modified animals. This division includes growing of crops in open fields as well in greenhouses."
						+ " Group 01.5 (Mixed farming) breaks with the usual principles for identifying main activity. It accepts that"
						+ " many agricultural holdings have reasonably balanced crop and animal production, and that it would be arbitrary "
						+ "to classify them in one category or the other.",
				"This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.",
				"A",
				"Agricultural activities exclude any subsequent processing of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages)"
						+ " and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. The preparation of products for the primary markets is included here. "
						+ "The division excludes field construction (e.g. agricultural land terracing, drainage, preparing rice paddies etc.) classified in section F (Construction) and buyers and cooperative associations engaged in the marketing of "
						+ "farm products classified in section G. Also excluded is the landscape care and maintenance, which is classified in class 81.30.",
				"1");
	}
	
	@Test
	public void testSave() {
		when(repo.save(entity)).thenReturn(entity);
		Nace nace = impl.saveNaceInfo(entity);
		assertThat(nace).isNotNull();
		assertThat(nace.getOrder()).isEqualTo(398482L);
	}
	
	@Test
	public void testGetNaceByOrder() {
		when(repo.findByOrder(398482L)).thenReturn(entity);
		Nace nace = impl.getNaceByOrder(398482L);
		assertThat(nace).isNotNull();
		assertThat(nace.getOrder()).isEqualTo(398482L);
	}
	
	@Test
	public void testWhenNaceNotFound() {
		when(repo.findByOrder(398483L)).thenReturn(null);
		NaceNotFoundException ex = Assertions.assertThrows(NaceNotFoundException.class, () -> impl.getNaceByOrder(398483L));
		assertThat(ex.getMessage()).isEqualTo("No Nace found for order - 398483");
		verify(repo, times(1)).findByOrder(398483L);
	}
	
	@AfterAll
	void cleanUp() {
		entity = null;
		impl = null;
		repo = null;
	}
}
