package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.enumeration.Role;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.lab.model.exception.PasswordDoNotMatch;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.implementation.AuthServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthServiceImplementation authServiceImplementation;
    private final UserService userService;

    public RegisterController(AuthServiceImplementation authServiceImplementation, UserService userService) {
        this.authServiceImplementation = authServiceImplementation;
        this.userService = userService;
    }


    @GetMapping
    public String vrati(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentException | PasswordDoNotMatch exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
