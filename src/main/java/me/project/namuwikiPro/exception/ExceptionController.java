package me.project.namuwikiPro.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class ExceptionController implements ErrorController {

    @ExceptionHandler(CustomException.class)
    public String error(){
        return "/loginUser/register";
    }

}
