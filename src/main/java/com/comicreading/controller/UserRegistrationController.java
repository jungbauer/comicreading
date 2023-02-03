package com.comicreading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.comicreading.domain.User;
import com.comicreading.security.UserAlreadyExistException;
import com.comicreading.security.UserDto;
import com.comicreading.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserRegistrationController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("/user/registration")
    public String showRegistrationForm(UserDto userDto) {
        log.debug(" =============================== Rendering bean backed registration page.");
        return "user/userRegistration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        log.debug(" =============================== registration POST.");
        log.debug(userDto.toString());
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.toString());
            return "user/userRegistration";
        }

        try {
            User registered = userService.registerNewUserAccount(userDto);
            model.addAttribute("regUsername", registered.getFirstName() + " " + registered.getLastName());
            model.addAttribute("regEmail", registered.getEmail());
        } catch (UserAlreadyExistException uaeEx) {
            bindingResult.addError(new ObjectError("user", "An account for that username/email already exists."));
            return "user/userRegistration";
        }
        catch (Exception ex) {
            return "error";
        }

        return "user/userRegistrationSuccess";
    }
}
