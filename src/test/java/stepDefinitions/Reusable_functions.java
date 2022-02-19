package stepDefinitions;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Reusable_functions {
	
	public static void main(String[] args) throws Exception {
//		WebDriver driver ;
//		System.setProperty("webdriver.chrome.driver",data.Configure_data.browser_driver_path);
//		driver = new ChromeDriver();
//		//goto url
//		driver.get("https://www.browserstack.com");
//		take_screenshot(driver,"D:\\Auto Test\\test images","test");
		
		api_response_from_get();
		}
	
	
	/*This function will take screenshot 
	 * @param webdriver
	 * @param folder_path_to_save
	 * @parm name: the main file name
	 * @throws Exception
	 * 
		Make sure you installed apache on your device
	 */
	
	
	public static void take_screenshot(WebDriver webdriver,String folder_path_to_save, String name) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//set file type
		String file_type = ".png";
		//set file name
		String file_name = get_current_time()+"_"+ name + file_type;
		//Move image file to new destination
		File DestFile=new File(folder_path_to_save+"\\"+file_name);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		}
	
	public static String get_current_time() {
		//get current_time
		LocalDateTime current_time_before_formatted 		= LocalDateTime.now();
		//initiate time format
		DateTimeFormatter date_time_format		= DateTimeFormatter.ofPattern("yyyy-MM-dd--kk-mm-ss");
		//format time to save file
		String current_time	= current_time_before_formatted.format(date_time_format);
		
		return current_time;
	}
	
	public static void api_response_from_get() {
		//make sure you installed rest assured 
		String bear_token	= "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOGVjMmJlN2IzMGVjNjNmODdlNjUyNTY3N2QwOTczNmM0NzcyMDY0M2ZiMmU3YmIxYmI5ODdiOTBkM2RhOWIwODA4Y2M4Nzc4OGFiYjhmNDAiLCJpYXQiOjE2NDUyODY3NjcsIm5iZiI6MTY0NTI4Njc2NywiZXhwIjoxNjQ2NTgyNzY2LCJzdWIiOiI0NDYiLCJzY29wZXMiOltdfQ.Wp9sjHT63Ke_N6HokcPMK4onV6i-jrgxE65Bue16h4vGH-ZF1cR4WdHvD-KZnxiTaRHSzxsHGkzF8H1lEZX1nXe6PR3Nc6znruTHkFR1sGhKm1Nvjm8VlyAuNopXh9QqAqg9uzByInK411t5rjUG7--DE23nrrLq70GckJwGtlN9wSEQot4eC0Oa32vUYgh29qCFRklGDSIATN6AIR7-ttrjaTBqIV7FqkUl_T2egwr7DPT_rPBQUMMzNx1_vXI2VVyUkS7R0HCWNYgJtk4dNjyEHD1ciRILrgztWKZFQjZCIR3Sp19hoKa7y9l7jfWLfglDCzVreWTf9WDoVNfuSHaKcV6y0MybkYEaJiAFZ-gvgNiJVQFNjmBNLdCkf_bPN02pX0Q7uIJxeqKGHuubAdQznlnBbG17l914gZtmt3rEfy-64iid-gyVVAH7H61W1Je_yUKynGMdhAFHGAHfsqB9qEUYSrj20JRFvG_dRAFYfq-mjNf2ttYNILxSUXWsCk1Wo8SEQEQQup7MWNkzCyDU8YZyBV3cY_S3x6JbptooQwDO2tVR-f8Wy_39mBtZXzMpoBiMmKttsMkb-1KJz2I4_zpW4dY4gRnPadh1pTRYvmeXDRpy9MS0SUkZqheEcQbQFmctsgez2L0pPtTUVuH-mXJdltIjPnAT9HTUDoI";
		
		RestAssured.baseURI = "https://factorx-api.test6.amelacorp.com";
		RequestSpecification API_me = RestAssured
				.given()
				.header("Authorization",
				"Bearer "+ bear_token)
		         .contentType(ContentType.JSON);
//		GET request structure
		Response response =  API_me
		         .when()
		         .get("/api/v1/me");
	
		System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());
		
		JsonPath response_jpath = new JsonPath(response.asPrettyString());
		System.out.println(response_jpath.getString("data.id"));
//		JsonPath response_jpath_2 = new JsonPath(response_jpath.getString("data"));
//		System.out.println(response_jpath_2);
//		System.out.println(response_jpath_2.getString("id"));

//		// Specify the base URL to the RESTful web service 
//		RestAssured.baseURI = "https://factorx-api.test6.amelacorp.com"; 
//		// Get the RequestSpecification of the request to be sent to the server. 
//		RequestSpecification API_me = RestAssured.given(); 
//		// specify the method type (GET) and the parameters if any. 
//		//In this case the request does not take any parameters 
//		Response response = API_me.request(Method.GET, "");
//		// Print the status and message body of the response received from the server 
//		System.out.println("Status received => " + response.getStatusLine()); 
//		System.out.println("Response=>" + response.prettyPrint());
	}
	
}
