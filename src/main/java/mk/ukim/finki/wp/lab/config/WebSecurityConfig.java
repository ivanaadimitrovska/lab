//package mk.ukim.finki.wp.lab.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final PasswordEncoder passwordEncoder;
//    private final UsernamePasswordProvider usernamePasswordProvider;
//
//    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsernamePasswordProvider usernamePasswordProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.usernamePasswordProvider = usernamePasswordProvider;
//    }
//            //TUKA VIDI SO PERMITALL DALI KOGA NE E NAJAVEN KE MOZHE DA JA VIDI STRANATA
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/", "/courses", "/assets/**", "/login", "/addStudent", "/listCourses")
//                .permitAll()
//                .antMatchers("/courses").anonymous()
////                .antMatchers("/admin/**").hasRole("ADMIN").and()
////                .formLogin().loginPage("/login").permitAll()
////                .failureForwardUrl("/login?error=Bad Credentials")
////                .defaultSuccessUrl("/courses", true)
//                .and().logout().logoutUrl("/logout").clearAuthentication(true)
//                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login")
//                .and().exceptionHandling().accessDeniedPage("/access_denied");
//
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////            auth.inMemoryAuthentication().withUser("ivana.dimitrovska")
////                    .password(passwordEncoder.encode("id"))
////                    .authorities("ROLE_USER").and().withUser("admin")
////                    .password(passwordEncoder.encode("admin"))
////                    .authorities("ROLE_ADMIN");
//        auth.authenticationProvider(usernamePasswordProvider);
//    }
//}

package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UsernamePasswordProvider usernamePasswordProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, UsernamePasswordProvider usernamePasswordProvider) {
        this.passwordEncoder = passwordEncoder;
        this.usernamePasswordProvider = usernamePasswordProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/", "/courses", "/assets/**", "/login", "/addStudent", "/listcourses")
                .permitAll()
                //.antMatchers("/studentEnrollmentSummary").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN").and()
                .formLogin().loginPage("/login").permitAll()
                .failureForwardUrl("/login?error=Bad Credentials")
                .defaultSuccessUrl("/courses", true)
                .and().logout().logoutUrl("/logout").clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/access_denied");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().withUser("admin")
//                    .password(passwordEncoder.encode("admin"))
//                    .authorities("ROLE_ADMIN");
        auth.authenticationProvider(usernamePasswordProvider);
    }
}
