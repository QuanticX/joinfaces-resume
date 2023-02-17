package org.joinfaces;

import java.io.File;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Builder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractLoader {

	public static WebDriver webDriver;

	public static JavascriptExecutor jsExecutor;

	public static final Logger logger = LoggerFactory.getLogger(AbstractLoader.class);

	public static final String prePath = "src" + File.separatorChar + "test" + File.separatorChar + "resources" + File.separatorChar;

	static {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeAll
	public static void initSelenium(){
		webDriver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) webDriver;
	}

	@AfterAll
	public static void quitSelenium(){
		webDriver.quit();
	}

}
