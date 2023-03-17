package com.comicreading.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    
}
