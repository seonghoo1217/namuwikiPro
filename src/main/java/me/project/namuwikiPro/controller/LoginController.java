package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.namuwikiPro.DTO.MemberDto;
import me.project.namuwikiPro.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public String createUserForm(MemberDto dto, Model model){

        model.addAttribute("dto", dto);

        return "loginuser/register";
    }

    @PostMapping("/register")
    public String createUser(@Validated MemberDto dto, BindingResult result,Model model) {
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
                dto.setUnique(1);
                memberservice.createUser(dto);
            } catch (Exception e) {
                log.error(e.getMessage());
                dto.setUnique(0);
                model.addAttribute("dto",dto);
                return "redirect:/loginuser/register";
            }
            return "loginuser/login";
        }


    }





}
