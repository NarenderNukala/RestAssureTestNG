import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidatingResponse {
	
	public RequestSpecification httpRequest;
	public Response response;
	public int statusCode;
	public String statusLine;
	
	@BeforeMethod
	public void Setup() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		httpRequest = RestAssured.given();
	}
	
	@Test
	public void ValidSuccessStatusCode()
	{
		response = httpRequest.get("/Hyderabad");
		System.out.println(response.asString());
		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}
	
	@Test
	public void InValidSuccessStatusCode()
	{
		response = httpRequest.get("/73424938943");
		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode /*actual value*/, 400 /*expected value*/, "Correct status code returned");
	}
	
	@Test
	public void ValidatSuccessStatusLine()
	{
		response = httpRequest.get("/Hyderabad");
		statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
	}

}
