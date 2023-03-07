package com.comicreading.service;

import com.comicreading.domain.DatabaseLogs;
import com.comicreading.repository.DatabaseLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLogsService {

    @Autowired
    private DatabaseLogsRepository databaseLogsRepository;

    public void logMessage(String message) {
        DatabaseLogs log = new DatabaseLogs(message);
        databaseLogsRepository.save(log);
    }
}
