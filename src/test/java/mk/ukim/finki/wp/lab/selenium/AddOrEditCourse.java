package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.selenium.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement id_prof;
    private WebElement submit;

    public AddOrEditCourse(WebDriver driver) {
        super(driver);
    }

    public static CoursePage addCourse(WebDriver driver, String name, String description, String id_prof) {
        get(driver, "/courses/add");
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.id_prof.click();
        addOrEditCourse.id_prof.findElement(By.xpath("//option[. = '" + id_prof + "']")).click();

        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }



    public static CoursePage editCourse(WebDriver driver, WebElement editButton, String name, String description, String id_prof) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.id_prof.click();
        addOrEditCourse.id_prof.findElement(By.xpath("//option[. = '" + id_prof + "']")).click();


        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }

}
