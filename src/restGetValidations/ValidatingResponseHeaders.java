package restGetValidations;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidatingResponseHeaders {

	public RequestSpecification httpRequest;
	public Response response;
	
	@BeforeMethod
	public void Setup() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		httpRequest = RestAssured.given();
	}
		
	@Test
	public void ValidateResponseHeaders()
	{
		response = httpRequest.get("/Hyderabad");
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);
		
		String serverType =  response.header("Server");
		System.out.println("Server value: " + serverType);
		Assert.assertEquals(serverType /* actual value */, "nginx" /* expected value */);
		 
		String contentEncoding  = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + contentEncoding );
		Assert.assertEquals(contentEncoding  /* actual value */, "gzip" /* expected value */);
	}
	
	@Test
	public void PrintAllHeaders() 
	{
		response = httpRequest.get("/Hyderabad");
		Headers headers = response.getHeaders();
		for(Header header:headers) {
			System.out.println("Key: " + header.getName() + "\nValue: " + header.getValue());
		}
	}
}
