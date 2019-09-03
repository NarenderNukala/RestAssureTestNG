package restPostValidations;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidatePostRequest {
	
	public RequestSpecification httpRequest;
	public Response response;
	
	@BeforeMethod
	public void setup()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		httpRequest = RestAssured.given();
	}
	
	@Test
	public void ValidatePostRequest()
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Vihaan1");  //Change the Name details to Test it again
		requestParams.put("LastName", "Nukala");
		requestParams.put("UserName", "VihaanN1");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "vihaan1@gmail.com");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/register");
		
		int statusCode = response.getStatusCode();
		System.out.println("Response " + response.asString());
		Assert.assertEquals(statusCode, 201);
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
	}
	
	@Test
	public void InValidatePostRequest()
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Vihaan1");  //Change the Name details to Test it again
		requestParams.put("LastName", "Nukala");
		requestParams.put("UserName", "VihaanN1");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "vihaan1@gmail.com");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.get("/register");
		
		int statusCode = response.getStatusCode();
		System.out.println("Invalid Response " + response.asString());
	}

}
