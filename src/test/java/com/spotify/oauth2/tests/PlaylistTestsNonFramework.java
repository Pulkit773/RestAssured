package com.spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTestsNonFramework {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQDGphlE9lnmeGu63fTFY-33SjkibIvBba4VmR6z3fOFBGEiwxfzRzlQnJTrAdtKNSBiITbFoII5AkO2BWkXQav-eJZnyG-CLFKdRdMcRo-uzGho60Oc5Nm2Xgz1hb0_ew4tSO5NCCzLTCiCvpntsb5D_2IN5f1kARlHgHr-uXveV6avOir3gB7u58mjHgmesBAYgo9fMb5xZLHwBdp-PLLEoL3r3BPgUM6KZcMYFoqIk08LuvxU2bi3DeDreiUg66So1-EvuhB6vRoD";

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                //expectContentType(ContentType.JSON).
                        log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void shouldBeAbleToCreateplaylist() {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification).
                body(payload).
                when().post("users/31rf3skpk3sdus37q7ujfr3eqh4m/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(201).
                body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetPlaylist() {
        given(requestSpecification).
                when().get("/playlists/60B55tqiGfAdC3hhTq65UN").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200).
                body("name", equalTo("New Playlist 1"),
                        "description", equalTo("New playlist description"));
    }

    @Test
    public void shouldBeAbleToUpdatePlayLists() {
        String payload = "{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
                when().put("/playlists/2Iky6h9qx1lt7mnbiipIIp").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }

    @Test
    public void shouldNotBeAbleToCreateplaylistWithoutName() {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification).
                body(payload).when().post("users/31rf3skpk3sdus37q7ujfr3eqh4m/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(400).
                body("error.message", equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreateplaylistWithExpireToken() {
        String payload = "{\n" +
                "    \"name\": \"New PlayList\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given().baseUri("https://api.spotify.com").basePath("/v1").
                header("Authorization", "Bearer " + "12345").contentType(ContentType.JSON).log().all().
                body(payload).when().post("users/31rf3skpk3sdus37q7ujfr3eqh4m/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(401).
                body("error.message", equalTo("Invalid access token"));
    }

}

