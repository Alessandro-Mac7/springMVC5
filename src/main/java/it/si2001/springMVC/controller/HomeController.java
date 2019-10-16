package it.si2001.springMVC.controller;

import it.si2001.springMVC.model.Category;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.service.CategoryService;
import it.si2001.springMVC.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(ModelMap model, HttpSession session){
        String email = (String) session.getAttribute("loggedUser");
        if(email!=null){
            List<User> customers = userService.getCustomers();
            List<Category> categories = categoryService.getAll();

            model.addAttribute("customers",customers);
            model.addAttribute("categories",categories);
            return "admin-home";
        } else
            return "welcome";
    }

}
