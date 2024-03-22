package org.example.week05.hotel_exercise;

import io.restassured.RestAssured;
import org.example.week05.hotel_exercise.config.ApplicationConfig;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @BeforeAll
    static void setUpAll() {
        RestAssured.baseURI = "http://localhost:7777/api";


    }

    @AfterAll
    static void tearDownAll() {
        ApplicationConfig.stopServer();
    }



    @Test
    @DisplayName("Test if the server is running")
    void testServerIsRunning() {
        RestAssured
                .given()
                .when()
                .get("/")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test GET /hotel")
    void testGetHotel() {
        RestAssured
                .given()
                .when()
                .get("/hotel")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test POST /hotel")
    void testPostHotel() {
        RestAssured
                .given()
                .contentType("application/json")
                .body("{\"name\":\"h4\",\"address\":\"Street 4\"}")
                .when()
                .post("/hotel")
                .then()
                .statusCode(201)
                .body("name", equalTo("h4"))
                .body("address", equalTo("Street 4"));
    }

    @Test
    @DisplayName("Test GET /room")
    void testGetRoom() {
        RestAssured
                .given()
                .when()
                .get("/room")
                .then()
                .statusCode(200)
                .body("number", contains(101, 102, 103));

    }

    @Test
    @DisplayName("Test POST /room")
    void testPostRoom() {
        RestAssured
                .given()
                .contentType("application/json")
                .body("{\"number\":101,\"price\":100.0}")
                .when()
                .post("/room")
                .then()
                .statusCode(201)
                .body("number", equalTo(101))
                .body("price", equalTo(100.0f));

    }


    @Test
    @DisplayName("Test GET /hotel/{id}")
    void testGetHotelById() {
        RestAssured
                .given()
                .when()
                    .get("/hotel/1")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body("name", equalTo("h1"))
                    .body("address", equalTo("Street 1"));


    }

    @Test
    @DisplayName("Test PUT /hotel/{id}")
    void testPutHotel() {
        RestAssured
                .given()
                .contentType("application/json")
                .body("{\"name\":\"Hotel California\",\"address\":\"1234 California St\"}")
                .when()
                .put("/hotel/4")
                .then()
                .statusCode(200)
                .body("name", equalTo("Hotel California"));
    }


    @Test
    @DisplayName("Test GET /hotel/{id}/rooms")
    void testGetHotelRooms() {
        RestAssured
                .given()
                .header("Bearer","khffghigj")
                .when()
                .get("/hotel/1/rooms")
                .then()
                .statusCode(200)
                .body("number", contains(1, 2))
                .body("price", contains(100.0f, 1000.0f));

    }



}