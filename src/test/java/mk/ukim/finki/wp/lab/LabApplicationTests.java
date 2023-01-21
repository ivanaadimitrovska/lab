package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.Role;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {
        MockMvc mockMvc;

        @Autowired
        CourseService courseService;

        @Autowired
        TeacherService teacherService;

        @Autowired
        StudentService studentService;

        @Autowired
        UserService userService;

        private static Teacher teacher;
        private static Student student;
        private static boolean dataInitialized = false;



        @BeforeEach
        public void setup(WebApplicationContext webApplicationContext){
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            initData();
        }

        public void initData(){
            if (!dataInitialized) {
                teacher = teacherService.addTeacher("ime1", "prezime1");
                teacherService.addTeacher("ime1", "prezime2");

//                m1 = manufacturerService.save("m1", "m1").get();
//                manufacturerService.save("m2", "m2");

                String user = "user";
                String admin = "admin";

                userService.register(user, user, user, user, user, Role.ROLE_USER);
                userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
                dataInitialized = true;
            }
        }
             @Test
            void contextLoads() {
            }


    @Test
        public void testGetCourses() throws Exception {
            MockHttpServletRequestBuilder courseRequest= MockMvcRequestBuilders.get("/courses");
            mockMvc.perform(courseRequest).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.model().attributeExists("lista"))
                    .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listCourses"))
                    .andExpect(MockMvcResultMatchers.view().name("master-template"));
        }

        @Test
        public void testDeleteCourse() throws Exception {
            Course course=courseService.save("test", "test_desc", teacher.getId());
            MockHttpServletRequestBuilder courseDeleteRequest= MockMvcRequestBuilders.delete("/courses/delete/"+course.getCourseId());
            mockMvc.perform(courseDeleteRequest).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));
        }

}
