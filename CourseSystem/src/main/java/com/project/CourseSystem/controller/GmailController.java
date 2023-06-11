package com.project.CourseSystem.controller;

import com.project.CourseSystem.converter.System_AccountConverter;
import com.project.CourseSystem.dto.SystemAccountDTO;
import com.project.CourseSystem.entity.EmailDetails;
import com.project.CourseSystem.entity.SystemAccount;
import com.project.CourseSystem.service.AccountService;
import com.project.CourseSystem.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GmailController {

    private AccountService accountService;

    private System_AccountConverter system_accountConverter;

    private EmailService emailService;

    private SystemAccountDTO system_AccountDTO;

    public GmailController(AccountService accountService, System_AccountConverter system_accountConverter,
                           EmailService emailService, SystemAccountDTO system_AccountDTO) {
        this.accountService = accountService;
        this.system_accountConverter = system_accountConverter;
        this.emailService = emailService;
        this.system_AccountDTO = system_AccountDTO;
    }

    @PostMapping("/verifyCodeSendForChangePassword")
    public String verification(@ModelAttribute SystemAccountDTO systemAccountDTO , Model model, HttpServletRequest request
            , HttpServletResponse response){
        if(!accountService.isGmailExist(systemAccountDTO.getGmail())){
            return "redirect:/resetPassword?errorGmail";
        }
        else {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(systemAccountDTO.getGmail());
            emailDetails.setSubject("Please verify your password reset request");
            String verificationCode = accountService.generateVerificationCode();
            String msgBody = "Your verification code is: " + verificationCode +"\n";
            msgBody += "Thank you";
            emailDetails.setMsgBody(msgBody);
            emailDetails.getAttachment();
            SystemAccount systemAccount = accountService.findByGmail(systemAccountDTO.getGmail());
            systemAccount.setVerificationCode(verificationCode);

            accountService.updateUser(system_accountConverter.convertEntityToDTO(systemAccount));

            emailService.sendSimpleEmail(emailDetails);
            model.addAttribute("system_account", system_AccountDTO);
            HttpSession session = request.getSession();
            session.setAttribute("change", "password");
            return "verify";
        }
    }

    @PostMapping("/verifyCodeSendForChangeGmail")
    public String gmailVerification(@ModelAttribute SystemAccountDTO systemAccountDTO , Model model, HttpServletRequest request
            , HttpServletResponse response){
        HttpSession session = request.getSession();
        if(accountService.isGmailExist(systemAccountDTO.getGmail())){
            return "redirect:/userProfile?errorGmail";
        }
        else {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(systemAccountDTO.getGmail());
            emailDetails.setSubject("Please verify your gmail change request");
            String verificationCode = accountService.generateVerificationCode();
            String msgBody = "Your verification code is: " + verificationCode +"\n";
            msgBody += "Thank you";
            emailDetails.setMsgBody(msgBody);
            emailDetails.getAttachment();

            String accountName = (String) session.getAttribute("CSys");

            accountService.updateVerifyCode(verificationCode, accountName);

            emailService.sendSimpleEmail(emailDetails);
            model.addAttribute("system_account", system_AccountDTO);

            session.setAttribute("newGmail", systemAccountDTO.getGmail());
            session.setAttribute("change", "gmail");
            return "verify";
        }
    }

    public String registrationVerification(HttpServletRequest request
            , HttpServletResponse response){
        HttpSession session = request.getSession();

        return null;
    }

    @GetMapping("/verify")
    public String verify(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("system_account", system_AccountDTO);
        return "verify";
    }

    @PostMapping("/verification/confirm")
    public String confirmation(@ModelAttribute SystemAccountDTO systemAccountDTO ,Model model, HttpServletRequest request,
                               HttpServletResponse response){
        SystemAccount systemAccount = accountService.findByVerificationCode(systemAccountDTO.getVerificationCode());

        if(systemAccount!=null){
            HttpSession session = request.getSession();
            String change = (String) session.getAttribute("change");
            if(change.equals("password")){
                session.removeAttribute("change");
                session.setAttribute("CSys", systemAccount.getAccountName());
                return "redirect:/changePassword";
            } else {
                String accountName = (String) session.getAttribute("CSys");
                accountService.updateGmail((String) session.getAttribute("newGmail"), accountName);
                session.removeAttribute("newGmail");
                session.removeAttribute("change");
                return "redirect:/profile";
            }
        }
        else {
            return "redirect:/verify?error";
        }
    }
}
