package me.project.namuwikiPro.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.namuwikiPro.DTO.MemberDto;
import me.project.namuwikiPro.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("loginuser")
@RequiredArgsConstructor
public class LoginController{

    @Autowired
    private final Memberservice memberservice;


    @GetMapping("/member")
    public String member(){
        return "loginuser/member";
    }

    @GetMapping("/allow")
    public String allow(){
        return "loginuser/allow";
    }

    @GetMapping("/admin")
    public String admin()
    {
        System.out.println("admin login");
        return "redirect:/";
    }
    @GetMapping("/login")
    public String loginForm(){

        return "/loginuser/login";
    }



    @GetMapping("/register")
    public String createUserForm(){

        return "loginuser/register";
    }

    @PostMapping("/register")
    public String createUser(@Validated MemberDto dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                log.error(error.getDefaultMessage());
                log.error(dto.toString());
            }

            throw new ValidationException("회원가입 에러!", (Throwable) errorMap);
        } else {
            try {
                memberservice.createUser(dto);
            } catch (Exception e) {
                log.error(e.getMessage());
                return "redirect:/loginuser/register";
            }


            return "loginuser/login";
        }


    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String username){
        return ResponseEntity.ok(memberservice.checkEmailDuplicate(username));
    }
}
