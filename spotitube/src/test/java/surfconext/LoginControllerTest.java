package surfconext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import javax.json.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;
import nl.han.oose.dea.rest.presentation.controllers.Config;
import nl.han.oose.dea.rest.presentation.controllers.LoginController;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import net.minidev.json.JSONObject;
import nl.han.oose.dea.rest.business.services.LoginService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginControllerTest {

  @Mock
  private LoginService loginService;

  @InjectMocks
  private LoginController loginController;

  public LoginControllerTest() {
    MockitoAnnotations.initMocks(this);
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "/spotitube";
  }

  @Test
  void shouldReturnAuthorizationUrl() {
    Response response = given()
      .when()
      .get("/han_login");

    response.then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("url", startsWith("https://connect.test.surfconext.nl/oidc/authorize"));
  }

//  @Test
//  public void shouldRedirectToSurfconextAppWithAccessToken() {
//    // Define the expected values
//    String expectedCode = "test_code";
//    String expectedAccessToken = "test_access_token";
//
//    // Set up the test environment
//    RestAssured.baseURI = "http://localhost:8080";
//    RestAssured.basePath = "/spotitube";
//
//    // Make the request and capture the response
//    Response response = RestAssured
//      .given()
//      .queryParam("code", expectedCode)
//      .queryParam("state", "test_state")
//      .when()
//      .get("/callback")
//      .then()
//      .extract()
//      .response();
//
//    // Verify the response
//    assertThat(response.getStatusCode(), equalTo(302));
//    assertThat(response.getHeader("Location"), equalTo("https://surfconext.netlify.app/?accessToken=" + expectedAccessToken));
//  }

  @Test
  void testCreateConfig() {
    // Arrange
    Config config = loginController.createConfig();

    assertNotNull(config.getIssuer());
    assertNotNull(config.getClientId());
    assertNotNull(config.getClientSecret());
    assertNotNull(config.getAuthorizationEndpoint());
    assertNotNull(config.getTokenEndpoint());
    assertNotNull(config.getRedirectUrl());
  }
}





