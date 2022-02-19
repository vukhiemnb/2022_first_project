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
		
		RestAssured.baseURI = "https://factorx-api.test6.amelacorp.com";
		RequestSpecification API_me = RestAssured.given();
//		GET request structure
		
		String bear_token	= "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZjYwZjJkMTc5NjBmNmZlZjAyMWY5MzQ4MzY2YzdjMzczYmRhOWY4ZTdjMWZmY2U0ODAzNTY5ZWU0YzI4Yjg5NzJlNjk0MDhkYzU1Y2RhM2QiLCJpYXQiOjE2NDUxNzkyNTIsIm5iZiI6MTY0NTE3OTI1MiwiZXhwIjoxNjQ2NDc1MjUyLCJzdWIiOiI1MzEiLCJzY29wZXMiOltdfQ.fND_bXdi4YsxwqroWElY9DuzgELCmvtF2Q-NggPt5EAb7I5rUG-g64CBZWi-Sjg-er6DGAU5ZgBKaWVVeimGmBjUVaAYgd7Vb3r96sAf7A139VUcGiwmPAzgWZ2NDbrmef3i9WAwYoDe8AWc0FbgpDUeVXVfTxsHuar7MAUnaFPzPwDPOYfq9mI-0BJ8DrCuRG-CVnW-HXJn_Qk3uBoR0wBB4IxjKAR_o3OEwzy3WHERxZz6yWgAcmPEtO6iWP_rB_e02Eg8nEU4VXR7CFAt0Ts9wGNE5cLUZhRS93oPUXO22JuT9fx7WeR1-FW68mDsBpassSlyX_oRgw-1PpyExSjapZMuGKFZiUbCiGLqk5mmqdhOl4s92rHgB6NKZOm00X3D3X32L7FUylEY_W39SdTZ3xQiIZ4xuoOtBEbsYSBCrcPg9RVhS9qwX1gPmqV5tyMCzbiJ5FaEEaLCPXQXazdVNgMXm4X2yWx2zdhCAZnzIEUr90xDonjqZlCaW2skFdCzPl_lXZKRC7OMm31ZLcNcZRdWzV3UETEz07uLYR3qGfp5rJNoUdGWTzRpp8IpdZaaKaYdktEcfm7yENPxwXQy4j7tHxruRV_tk4wKSoSTOkRvjM7VhUmRPzETgVrZTbJC_HxDmE5hX0gwOCKCGu6OhfzKwe0tLqs6Tlvtl0c";
		Response response =  API_me
				.header("Authorization",
							"Bearer "+ bear_token)
		         .contentType(ContentType.JSON)
//		         .pathParam("id", "AskJsd8Sd")
		         .when()
		         .get("/api/v1/me");
	
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeader(bear_token));
		System.out.println("abc");

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
