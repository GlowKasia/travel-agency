package pl.sdajava25.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.User;
import pl.sdajava25.travelagency.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("user/add/{tripId}")
    public String addNewUser(@PathVariable("tripId") Long id,
                             Model model){
        model.addAttribute("newUser", new User());
        return "/user/add";
    }

    @PostMapping("/user/add/{tripId")
    public String addNewUserPost(@PathVariable("tripId") Long id,
                                 @ModelAttribute("newUser") User user){
        userService.addNewUser(id, user);
        return "redirect:/trip/your-purchase";
    }
}
