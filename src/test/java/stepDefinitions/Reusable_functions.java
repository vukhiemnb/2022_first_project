package stepDefinitions;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Reusable_functions {
	
	public static void main(String[] args) throws Exception {
//		WebDriver driver ;
//		System.setProperty("webdriver.chrome.driver",data.Configure_data.browser_driver_path);
//		driver = new ChromeDriver();
//		//go to url
//		driver.get("https://www.browserstack.com");
//		take_screenshot(driver,"D:\\Auto Test\\test images","test");
		

		Response api_login	= api_response_from_post("/api/v1/login", "", "{\r\n"
				+ "    \"client_id\": 1,\r\n"
				+ "    \"client_secret\": \"mYTTkjjLheDGRaBTQzQEFkyEpP1UmaXm3TGRNUPz\",\r\n"
				+ "    \"grant_type\": \"password\",\r\n"
				+ "    \"username\": \"factory_company_5\",\r\n"
				+ "    \"password\": \"Password@123\"\r\n"
				+ "}");
		//declare token
		String bear_token	= get_data_from_api_response(api_login, "data.access_token");
		
		Response api_payment	= api_response_from_post("/api/v1/payment_infos",bear_token,
				"{\r\n"
				+ "    \"payment_info\": {\r\n"
				+ "        \"bank_id\": 1,\r\n"
				+ "        \"bank_branch_id\": 1220,\r\n"
				+ "        \"account_type\": \"普通\",\r\n"
				+ "        \"account_number\": \"1234567\",\r\n"
				+ "        \"account_holder_name_kana\": \"ｱ\"\r\n"
				+ "    }\r\n"
				+ "}");
		System.out.println(api_payment.asPrettyString());
		}
	
	
	/*This function will take screenshot 
	 * @param webdriver
	 * @param folder_path_to_save
	 * @param name: the main file name
	 * @throws Exception
	 * Make sure you installed apache on your device
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
	
	
	/*This function will return current time with format yyyy-mm-dd--hh-mm-ss 
	 */
	public static String get_current_time() {
		//get current_time
		LocalDateTime current_time_before_formatted 		= LocalDateTime.now();
		//initiate time format
		DateTimeFormatter date_time_format					= DateTimeFormatter.ofPattern("yyyy-MM-dd--kk-mm-ss");
		//format time to save file
		String current_time	= current_time_before_formatted.format(date_time_format);
		
		return current_time;
	}
	
	/*This function will return response from API GET 
	 * @param request_uri
	 * @param request_token
	 * Make sure you add rest assured dependency
	 */	
	public static Response api_response_from_get(String request_uri, String request_token) {				
		//set base URI
		RestAssured.baseURI = data.Configure_data.baseURI;		
		//create request
		RequestSpecification API_me = RestAssured
										.given()
										//set token
										.header("Authorization",
										"Bearer "+ request_token)
										//set content-type
								        .contentType(ContentType.JSON);
		//GET request structure
		Response response =  API_me
		         				.when()
		         				//add method and send request
		         				.get(request_uri);
		
		return(response);
	}
	
	/*This function will return value of a key from input JSON response 
	 * @param response_input
	 * @param data_path
	 * Make sure you add JSON dependency
	 */	
	public static String get_data_from_api_response(Response response_input, String data_path) {
		JsonPath response_json_path = new JsonPath(response_input.asPrettyString());
		String data_output			= response_json_path.getString(data_path);
		return data_output;
	}
	
	/*This function will return value of a key from input JSON response 
	 * @param request_uri
	 * @param request_token
	 * @param request_body
	 * Make sure you add rest assured and JSON dependency
	 */	
	public static Response api_response_from_post(String request_uri, String request_token, String request_body) {
		//make sure you installed rest assured
		
		//set base URI
		RestAssured.baseURI = data.Configure_data.baseURI;
		
		//set request param
		//make sure you add json dependency		
		JSONObject request_body_Obj	= convert_json_from_string(request_body);
		
		//create request
		RequestSpecification API_me = RestAssured
										.given()
										//set token
										.header("Authorization",
										"Bearer "+ request_token)
										//set content-type
								        .contentType(ContentType.JSON);										
		
//		POST request structure
		Response response =  API_me
		         				.when()
		         				//add body
		         				.body(request_body_Obj.toString())
		         				//add method and send request
		         				.post(request_uri);		
		return response;
	}
	
	/*This function will return JSON from input JSON string input
	 * @param input_json_string
	 * Make sure you add JSON dependency
	 */	
	public static JSONObject convert_json_from_string(String input_json_string) {
		    JSONObject jsonObject 	= new JSONObject(input_json_string);
		    return jsonObject;
		
	}
	
}
