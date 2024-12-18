package hibernate.pp_3_1_1.controller;

import hibernate.pp_3_1_1.model.User;
import hibernate.pp_3_1_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users.isEmpty() ? null : users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());

        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user-create";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User created successfully!");
        return "redirect:/users";
    }


    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user-update";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/users";
    }
}