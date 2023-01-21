package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumeration.Role;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;

    private HtmlUnitDriver htmlUnitDriver;
    private static Teacher teacher1;
    private static Teacher teacher2;
    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = false;
    private static String user = "user";
    private static String admin = "admin";

    @BeforeEach
    public void setUp(){
        htmlUnitDriver=new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if(this.htmlUnitDriver!=null){
            this.htmlUnitDriver.close();
        }
    }

    public void initData(){
        if (!dataInitialized) {
            teacher1 = teacherService.addTeacher("ime1", "prezime1");
            teacher2=teacherService.addTeacher("ime2", "prezime2");

            regularUser=userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser=userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception{
        CoursePage coursePage=CoursePage.to(htmlUnitDriver);
        coursePage.assertElements(0,0,0,0);
        Login login=Login.openLogin(htmlUnitDriver);
        coursePage=Login.doLogin(htmlUnitDriver, login, adminUser.getUsername(), admin);
        coursePage.assertElements(0,0,0,1);
        coursePage = AddOrEditCourse.addCourse(htmlUnitDriver,"Web Programiranje","WPDesc", teacher1.getTeacherFullname().getName()+" "+teacher1.getTeacherFullname().getSurname());
        coursePage.assertElements(1,1,1,1);
        coursePage = AddOrEditCourse.addCourse(htmlUnitDriver,"Web Programiranje2","WPDesc2", teacher2.getTeacherFullname().getName()+" "+teacher2.getTeacherFullname().getSurname());
        coursePage.assertElements(2,2,2,1);
        coursePage.getDeleteButtons().get(1).click();
        coursePage.assertElements(1,1,1,1);
        coursePage=AddOrEditCourse.editCourse(htmlUnitDriver,coursePage.getEditButtons().get(0),"Web Programiranje", "WPDesc", teacher1.getTeacherFullname().getName()+" "+teacher1.getTeacherFullname().getSurname());
        coursePage.assertElements(1,1,1,1);

        login=Login.logout(htmlUnitDriver);
        coursePage=Login.doLogin(htmlUnitDriver, login, regularUser.getUsername(), user);
        coursePage.assertElements(1,0,0,0);
    }
}
