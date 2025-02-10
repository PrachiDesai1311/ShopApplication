package com.example.microservice.inventory;

import com.example.microservice.inventory.model.Inventory;
import com.example.microservice.inventory.repository.InventoryRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
			.withDatabaseName("inventory_service")
			.withUsername("root")
			.withPassword("mysql");
	@LocalServerPort
	private Integer port;

	@Autowired
	private InventoryRepository inventoryRepository;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		inventoryRepository.save(new Inventory(null, "iphone 12", 20));
		inventoryRepository.save(new Inventory(null, "iphone", 19));
	}

	static{
		mySQLContainer.start();
	}

	@Test
	public void isInStockTest() {
	  var response = RestAssured.given()
			  .when()
			  .get("/api/inventory?stuCode=iphone 12&quantity=19")
			  .then()
			  .log().all()
			  .extract().body().as(Boolean.class);
	  assertTrue(response);
	}
}
