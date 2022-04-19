package com.nace.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nace.app.model.Nace;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class NaceApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void saveAndRetrieveNace() {
		Nace entity = new Nace(398482L, 1L, "1", "A",
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
		
		// Scenario :  To save NACE record to db.
		this.webTestClient.post().uri("/nace/v1/add")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(entity), Nace.class)
		.exchange()
			.expectStatus().isCreated();
		
		
		String expectedOutput = "{\n" + " \"order\" :398482,\n" + " \"level\" :1,\n" + " \"code\" : \"1\",\n"
				+ " \"parent\" : \"A\",\n"
				+ " \"description\" : \"Crop and animal production, hunting and related service activities\",\n"
				+ " \"itemsIncluded\" : \"This division includes two basic activities, namely the production of crop products and production of animal products, covering also the forms of organic agriculture, the growing of genetically modified crops and the raising of genetically modified animals. This division includes growing of crops in open fields as well in greenhouses. Group 01.5 (Mixed farming) breaks with the usual principles for identifying main activity. It accepts that many agricultural holdings have reasonably balanced crop and animal production, and that it would be arbitrary to classify them in one category or the other.\",\n"
				+ " \"itemsAlsoIncluded\" :\"This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.\",\n"
				+ " \"rulings\" : \"A\",\n"
				+ " \"itemsExcluded\" :\"Agricultural activities exclude any subsequent processing of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages) and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. The preparation of products for the primary markets is included here. The division excludes field construction (e.g. agricultural land terracing, drainage, preparing rice paddies etc.) classified in section F (Construction) and buyers and cooperative associations engaged in the marketing of farm products classified in section G. Also excluded is the landscape care and maintenance, which is classified in class 81.30.\",\n"
				+ " \"referenceToISICRev4\" :\"1\"\n" + "}";
		
		// Scenario : To retrieve the NACE record by order from db.
		this.webTestClient.get().uri("/nace/v1/get/{order}", 398482L).exchange().expectStatus().isOk().expectBody()
				.json(expectedOutput);
	}
	
	@Test
	void testNaceNotFoundException() {
		this.webTestClient.get().uri("/nace/v1/get/{order}", 1L).exchange()
		.expectStatus()
		.is5xxServerError();
	}
	
	@AfterAll
	void cleanUp() {
		webTestClient = null;
	}

}
