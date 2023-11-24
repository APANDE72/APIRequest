package Task;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class ApiRequest {

	int id;
	
	
	@Test (priority=1)
	void getUsers() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
			
	}
	
	@Test (priority=2)
	void CreateUser() {
		
		HashMap data =new HashMap();
		data.put("Name", "Test");
		data.put("Job", "Tester");
		
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		//.then()
		//	.statusCode(201)
			//.log().all();
		
		
	}
	
	@Test(priority=3,dependsOnMethods= {"CreateUser"})
	void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name", "Demo");
		data.put("job","teacher");
		
		    given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.put("https://reqres.in/api/users/"+id)
				
		    .then()
		    	.statusCode(200)
		    	.log().all();
	}
	
}
