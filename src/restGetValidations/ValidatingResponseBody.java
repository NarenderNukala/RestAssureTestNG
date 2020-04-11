package restGetValidations;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ValidatingResponseBody {
	
	public RequestSpecification httpRequest;
	public Response response;
	
	@BeforeMethod
	public void setup()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		httpRequest = RestAssured.given();
		response = httpRequest.get("/Hyderabad");
	}
	
	
	@Test
	public void PrintResponseBody()
	{
		ResponseBody body = response.getBody();
		System.out.println("Response Body " + body.asString());
	}

	@Test
	public void PrintNodesUsingJson()
	{
		JsonPath jsonEvaluator = response.jsonPath();
		System.out.println("City : " + jsonEvaluator.get("City"));
		System.out.println("Humidity : " + jsonEvaluator.get("Humidity"));
		System.out.println("WindDirectionDegree : " + jsonEvaluator.get("WindDirectionDegree"));
	}
	
}
