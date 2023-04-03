package com.comicreading.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.comicreading.service.DatabaseLogsService;
import com.comicreading.domain.DatabaseLogs;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DatabaseLogsController {

    @Autowired
    private DatabaseLogsService databaseLogsService;

    @GetMapping("/admin/logs")
    public String viewLogs(Model model, Principal principal) {
        List<DatabaseLogs> logList = databaseLogsService.getAllLogs();
        model.addAttribute("logs", logList);
        return "admin/logs";
    }

    @GetMapping("/admin/deleteLog")
    public ModelAndView deleteLog(@RequestParam(name = "id") String logId, Model model, Principal principal) {
        //TODO this really needs a confirmation modal etc
        try {
            databaseLogsService.deleteLog(Integer.parseInt(logId));
            return new ModelAndView("redirect:/admin/logs");
        } catch (Exception e) {
            log.error("Error when deleting comic.", e);
            return new ModelAndView("error");
        }
        
    }
    
}
