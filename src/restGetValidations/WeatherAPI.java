package restGetValidations;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherAPI {
	
		/*Use the RestAssured class to generate a RequestSpecification for the 
		URL: http://restapi.demoqa.com/utilities/weather/city/Hyderabad
		Specify the HTTP Method type
		Send the Request to the Server
		Get the Response back from the server
		Print the returned Response’s Body
        */
	@Test
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent to the server.
		// The server is specified by the BaseURI that we have specified in the above step
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		
		//Response response = httpRequest.request(Method.GET, "/Hyderabad");
		//Directly we can use request type like get/post
		Response response = httpRequest.get("/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		System.out.println("Another way of getting response body");
		System.out.println("Response Body is =>  " + response.asString());

	}

}
