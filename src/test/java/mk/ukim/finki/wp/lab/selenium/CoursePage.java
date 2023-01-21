package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.List;

@Getter
public class CoursePage extends AbstractPage{

    @FindBy(css = ".add-course-btn")
    private List<WebElement> courseRows;

    @FindBy(css = ".names")
    private List<WebElement> courseNames;


    @FindBy(css = ".delete-course")
    private List<WebElement> deleteButtons;


    @FindBy(css = ".edit-course")
    private List<WebElement> editButtons;


    @FindBy(css = ".add-course")
    private List<WebElement> addCourseButton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public void assertElements(int coursesNumber, int deleteButtons, int editButtons, int addButtons) {
        Assert.assertEquals("rows do not match",coursesNumber, this.getCourseRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddCourseButton().size());
    }

//    public void Names(String courseName) {
//        Assert.assertSame("isti iminja",courseName, this.getCourseNames().stream().filter(c->c.getText().equals(courseName)));
//    }
}
