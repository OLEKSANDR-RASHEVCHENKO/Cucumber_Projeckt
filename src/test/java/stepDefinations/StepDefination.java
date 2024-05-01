package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Untils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class StepDefination extends Untils {
    RequestSpecification req;
    ResponseSpecification response1;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();

    public StepDefination() throws FileNotFoundException {
    }

    @Given("Add Place PayLoad with {string} {string} {string}")
    public void add_place_pay_load_with(String name, String language, String address) throws IOException {
        req =given().spec(requestSpecification()).log().all()
                .body(dataBuild.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response1= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response= req.when().post("/maps/api/place/add/json")
                .then().spec(response1).extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(keyValue).toString(),ExpectedValue);
    }
    @Then("{string} in responce body is {string}")
    public void in_responce_body_is(String keyValue, String ExpectedValue) {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(keyValue).toString(),ExpectedValue);
    }

}
