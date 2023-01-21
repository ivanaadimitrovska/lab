package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Course;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Login extends AbstractPage{

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public Login(WebDriver webDriver) {
        super(webDriver);
    }

    public static Login openLogin(WebDriver driver) {
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, Login.class);
    }

    public static CoursePage doLogin(WebDriver driver, Login login, String username, String password) {
        login.username.sendKeys(username);
        login.password.sendKeys(password);
        login.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public static Login logout(WebDriver driver) {
        get(driver, "/logout");
        return PageFactory.initElements(driver, Login.class);
    }
}
