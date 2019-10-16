package it.si2001.springMVC.controller;

import it.si2001.springMVC.dto.RegisterDTO;
import it.si2001.springMVC.model.Typology;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.service.TypologyService;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.util.PasswordUtil;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TypologyService typologyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/register")
    public String showRegister(Model model) {
        User user = new User();
        RegisterDTO userDTO = convertToDto(user);
        model.addAttribute("user", userDTO);
        return "register";
    }

    @GetMapping("/list")
    public String showUsers(Model model) {
        List <User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "list-users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute RegisterDTO registerDTO) {
        User newUser = convertToEntity(registerDTO);
        userService.saveUser(newUser);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/saveCustomer")
    public String saveUserAjax(@RequestBody RegisterDTO registerDTO) {
        User newUser = convertToEntity(registerDTO);
        userService.saveUser(newUser);
        return "success";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, Model model) throws ResourceNotFoundException {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "customer-form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/home";
    }

    private RegisterDTO convertToDto(User user) {
        RegisterDTO registerDTO = modelMapper.map(user, RegisterDTO.class);
        return registerDTO;
    }

    private User convertToEntity(RegisterDTO registerDTO) {
        User user = modelMapper.map(registerDTO, User.class);
        Typology typology;
        if (registerDTO.isAdmin()) {
            typology = typologyService.getType("Admin");
            user.setTypology(typology);
            String salt = PasswordUtil.getSalt(30);
            String encryptedPsw = PasswordUtil.generateSecurePassword(registerDTO.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(encryptedPsw);
        } else {
            typology = typologyService.getType("Customer");
            user.setTypology(typology);
            String salt = PasswordUtil.getSalt(30);
            String encryptedPsw = PasswordUtil.generateSecurePassword(registerDTO.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(encryptedPsw);
        }
        return user;
    }

}