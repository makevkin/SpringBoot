package web.controller;

import web.service.UserService;
import web.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/") // показать всех пользователей
    public String showAllUsers(Model model) {
        List<User> listUser = userService.getAllUsers();
        model.addAttribute("users", listUser);
        return "showAll";
    }

    @GetMapping("/add") // форма для добавления нового пользователя
    public String addUserForm(@ModelAttribute("newUser") User user) {
        return "addUser";
    }

    @PostMapping("/addUser") // добавление нового пользователя
    public String addUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("userId") int id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("newUser", user);
        return "addUser";
    }

    @GetMapping("/delete") // удаление пользователя по id
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}


