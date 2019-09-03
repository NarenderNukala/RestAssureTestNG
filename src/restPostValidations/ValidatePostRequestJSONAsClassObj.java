package restPostValidations;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ValidatePostRequestJSONAsClassObj {
	
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
		requestParams.put("FirstName", "Vihaan2");  //Change the Name details to Test it again
		requestParams.put("LastName", "Nukala");
		requestParams.put("UserName", "VihaanN2");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "vihaan2@gmail.com");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/register");
		ResponseBody body = response.getBody();
		
		RegistrationSuccessResponse  responseBody = body.as(RegistrationSuccessResponse .class);
		System.out.println("Success Code : " + responseBody.SuccessCode);
		System.out.println("Success Message : " + responseBody.Message);
		
		System.out.println(body.asString());
		Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
		Assert.assertEquals("Operation completed successfully", responseBody.Message);
		
		
	}

}
