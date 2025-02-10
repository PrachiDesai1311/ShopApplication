**Local Setup for product-service**

**Pre-Requisites:**
1. IDE(Eclipse, IntelliJ)
2. Java
3. Postman
4. Docker Desktop

**Setting up product-service locally:**
1. Clone the product-service into the IDE
2. Run the command mvn clean verify
3. Open IDE terminal and run the command docker-compose up -d to install the MongoDB
4. Once MongoDB started, start the application and test the two endpoints through the postman (check API Endpoint section for endpoint URL)

**API Endpoint Testing through Postman and Swagger**
A) Postman:
   1. To create product, select "POST" method in the postman and use below endpoint and JSON payload, 
      Endpoint URL : http://localhost:8080/api/product
      JSON Payload:
      {
        "name": "Apple Iphone",
         "description" : "5 Mega Pixel Camera",
         "price": 10000
      }
   2. To view list of available product, select "GET" method in the postman and hit the below endpoint,
      Endpoint URL : http://localhost:8080/api/product

B) Swagger-ui:
   Once application started successfully, hit the "http://localhost:8080/swagger-ui/index.html and test the endpoint.
