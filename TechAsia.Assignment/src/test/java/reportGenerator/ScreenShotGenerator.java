package reportGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;

public class ScreenShotGenerator {
	public static void generateScreenShot(Scenario S, WebDriver wd) throws IOException{
		File src = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		InputStream stream = new FileInputStream(src);
		S.embed(IOUtils.toByteArray(stream), "image/jpeg");
	}

}
