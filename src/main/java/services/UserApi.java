package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class UserApi {
  private static final String BASE_URL = System.getProperty("baseUrl", "https://petstore.swagger.io/v2");
  private static final String URL = System.getProperty("url","/user");
  private static final int STATUS_CODE = 200;

  public UserApi() {

    Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecification(STATUS_CODE));

  }

  public ValidatableResponse createUser(UserDTO user) {

    return given()
        .body(user)
        .when()
        .post(URL)
        .then().log().all()
        .body("message", equalTo(Long.toString(user.getId())))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
  }

  public UserDTO getUser(String username) {
    return given()
        .basePath(URL)
        .when().get(username)
        .then().log().all()
        .extract().jsonPath().getObject(".", UserDTO.class);

  }

  public ValidatableResponse deleteUser(String username){
    return given()
        .delete(URL +"/"+username)
        .then().log().all();
  }
}
