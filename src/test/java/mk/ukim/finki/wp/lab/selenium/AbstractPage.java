package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {

    WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    static void get(WebDriver driver, String relativeUrl){
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9991") + relativeUrl;
        driver.get(url);
    }
}
