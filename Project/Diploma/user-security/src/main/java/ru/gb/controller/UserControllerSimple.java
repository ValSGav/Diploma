package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.api.User;

@Controller
public class UserControllerSimple {

     @GetMapping("/login")
    public String login() {
        return "login"; // Страница login.html
    }

     @GetMapping("/register")
      public String register(Model model) {
          model.addAttribute( "user", new User() );
          return "register"; // Страница register.html
      }
}
