package it.si2001.springMVC.controller;

import it.si2001.springMVC.dto.UserLoginDTO;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(ModelMap model){
        User user = new User();
        UserLoginDTO userDTO = convertToDto(user);
        model.addAttribute("user", userDTO);
        return "welcome";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") UserLoginDTO userLoginDTO, HttpSession session, Model model){
        User user = userService.getUserByMail(userLoginDTO.getEmail());

        if(user!=null){
            String securePassword = user.getPassword();
            String salt = user.getSalt();
            boolean match = PasswordUtil.verifyUserPassword(userLoginDTO.getPassword(), securePassword, salt);

            if(match){
                session.setAttribute("loggedUser", userLoginDTO.getEmail());
                if(user.getTypology().getType().equals("Admin"))
                    return new ModelAndView("redirect:/home");
                else
                    return new ModelAndView("redirect:/home");
            } else {
                return new ModelAndView("redirect:/");
            }
        }
        return new ModelAndView("redirect:/");
    }

    private UserLoginDTO convertToDto(User user) {
        UserLoginDTO userLoginDTO = modelMapper.map(user, UserLoginDTO.class);
        return userLoginDTO;
    }

}
