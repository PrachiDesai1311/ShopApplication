**Local Setup for inventory-service**

**Pre-Requisites:**

1. IDE(Eclipse, IntelliJ)
2. Java
3. Postman
4. Docker Desktop

**Setting up inventory-service locally:**

1. Clone the order-service into the IDE
2. Run the command mvn clean verify
3. Open IDE terminal and run the command docker-compose up -d to install the MySQL Database
4. Start the application and test the endpoint through the postman (check API Endpoint section for endpoint URL)

**API Endpoint Testing through Postman and Swagger**

**A) Postman: **
Open Postman and hit the "http://localhost:8082/api/inventory?stuCode=samsung&quantity=1" endpoint.

**B) Swagger-ui:**
Once application started successfully, hit the "http://localhost:8082/swagger-ui/index.html and test the endpoint.

