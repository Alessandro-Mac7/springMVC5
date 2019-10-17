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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TypologyService typologyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String index(ModelMap model, HttpSession session){
        String logged = (String) session.getAttribute("loggedUser");

        if(logged!=null){
            User user = userService.getUserByMail(logged);
            RegisterDTO registerDTO = convertToDto(user);
            if(user.getTypology().getType().equals("Admin")){
                model.addAttribute("user",registerDTO);
                return "profile";
            } else {
                model.addAttribute("user",registerDTO);
                return "profile-customer";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        User user = new User();
        RegisterDTO userDTO = convertToDto(user);
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute RegisterDTO registerDTO, HttpSession session) {
        String logged = (String) session.getAttribute("loggedUser");
        if(logged!=null){
            User newUser = convertToEntity(registerDTO);
            userService.saveUser(newUser);
            return "redirect:/home";
        } else {
            User newUser = convertToEntity(registerDTO);
            userService.saveUser(newUser);
            return "redirect:/";
        }
    }

    @ResponseBody
    @PostMapping("/saveCustomer")
    public String saveUserAjax(@RequestBody RegisterDTO registerDTO) {
        User newUser = convertToEntity(registerDTO);
        userService.saveUser(newUser);
        return "success";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, HttpSession session, Model model) throws ResourceNotFoundException {
        String logged = (String) session.getAttribute("loggedUser");
        if(logged!=null){
            String typology =userService.getUserByMail(logged).getTypology().getType();
            User user = userService.getUser(id);
            RegisterDTO registerDTO = convertToDto(user);
            if(typology.equals("Admin")){
                model.addAttribute("user",registerDTO);
                return "profile";
            } else {
                model.addAttribute("user",registerDTO);
                return "profile-customer";
            }
        }
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/home";
    }

    private RegisterDTO convertToDto(User user) {
        RegisterDTO registerDTO = modelMapper.map(user, RegisterDTO.class);
        if (user.getTypology().getType().equals("Admin")) {
            registerDTO.setAdmin(true);
        } else {
            registerDTO.setAdmin(false);
        }
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
