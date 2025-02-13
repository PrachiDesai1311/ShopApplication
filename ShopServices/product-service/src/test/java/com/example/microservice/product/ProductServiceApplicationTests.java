package com.example.microservice.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static{
		mongoDBContainer.start();
	}

	@Test
	public void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "Apple Iphone 12",
					"description": "Apple Iphone 12 with 5G",
					"price": 1000
				}
				""";
		RestAssured.given()
			.contentType(ContentType.JSON)
			.body(requestBody).when()
			.post("/api/product")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Apple Iphone 12"))
				.body("description", Matchers.equalTo("Apple Iphone 12 with 5G"))
				.body("price", Matchers.equalTo(1000));
	}

}
