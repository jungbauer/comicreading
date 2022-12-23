package com.comicreading.comicreading.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController  {
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        //TODO add logging here
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
        
            // if(statusCode == HttpStatus.NOT_FOUND.value()) {
            //     return "error-404";
            // }
            // else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            //     return "error-500";
            // }
        }
        return "error";
    }
}
