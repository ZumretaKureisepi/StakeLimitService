# StakeLimitService

1. Clone this repository
2. Run `mvn clean install -DskipTests` inside the project folder
3. Run `docker-compose up` to bring up the Docker container for PostgreSQL and Spring Boot Web API.
4. Import the Postman collection containing the API documentation from file `StakeLimitService.postman_collection.json`.
5. Create a new device using the template provided in the `Create Device` API endpoint and copy the `id` field from the response.
6. Create a ticket message using the template provided in the `Create Ticket Message` API endpoint, and the `deviceId` generated in the previous step
7. (Optional) Update the device parameters using the template provided in the `Set device parameters` API endpoint, and remember to provide the `deviceId` generated in Step 5 in the URI of this endpoint.
