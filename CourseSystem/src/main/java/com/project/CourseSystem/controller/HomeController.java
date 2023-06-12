package com.project.CourseSystem.controller;

import com.project.CourseSystem.dto.SystemAccountDTO;
import com.project.CourseSystem.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private AccountService accountService;

    public HomeController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, HttpServletResponse response){
        return "home";
    }

    @PostMapping("/welcome")
    public String welcome(@ModelAttribute("system_account") SystemAccountDTO system_accountDTO, HttpServletRequest request, HttpServletResponse response,
                          Model model){
        HttpSession session = request.getSession();
        SystemAccountDTO system_accountDTO1 = accountService.findUser(system_accountDTO.getAccountName(), system_accountDTO.getAccountPassword());

        if(system_accountDTO1 != null){
            String check = system_accountDTO.getAccountPassword();
            //decrypt password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(check, system_accountDTO1.getAccountPassword())){

                session.setAttribute("CSys", system_accountDTO1.getAccountName());
                return "/home";
            }
            else{
                model.addAttribute("error", "Invalid Account Name or Password");
                return "login";
            }
        }
        else {
            model.addAttribute("error", "Unknown error");
            return "login";
        }
    }
}
