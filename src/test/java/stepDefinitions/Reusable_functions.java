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

public class Reusable_functions {
	
//	public static void main(String[] args) throws Exception {
//		WebDriver driver ;
//		System.setProperty("webdriver.chrome.driver",data.Configure_data.browser_driver_path);
//		driver = new ChromeDriver();
//		//goto url
//		driver.get("https://www.browserstack.com");
//		take_screen_shot(driver,"D:\\Auto Test\\test images","test");
//		}
	
	
	/*This function will take screenshot 
	 * @param webdriver
	 * @param folder_path_to_save
	 * @parm name: the main file name
	 * @throws Exception
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
	
}
