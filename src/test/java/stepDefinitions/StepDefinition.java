package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec ;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String langauge, String address) throws IOException {
		res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, langauge, address));

	}


	@When("User call {string} with {string} method")
	public void user_call_with_post_method(String resource ,String method) {
		APIResources resourceAPI =  APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource()  );
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
			response =res.when().post(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("GET")){
			response =res.when().get(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("DELETE")){
			response =res.when().delete(resourceAPI.getResource());
		}
				

	}

	@Then("The API call is Success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer code) {
	  assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {	
		assertEquals(getJsonPath(response,KeyValue),ExpectedValue);
		
		
	}
	
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		String place_id = getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_call_with_post_method(resource, "GET");
		String Actualname = getJsonPath(response,"name");
		assertEquals(name,Actualname);
	}


}
