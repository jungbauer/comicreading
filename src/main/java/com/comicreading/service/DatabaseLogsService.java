package com.comicreading.service;

import com.comicreading.domain.DatabaseLogs;
import com.comicreading.repository.DatabaseLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatabaseLogsService {

    @Autowired
    private DatabaseLogsRepository databaseLogsRepository;

    public void logMessage(String message) {
        log.debug(message);
        DatabaseLogs log = new DatabaseLogs(message);
        databaseLogsRepository.save(log);
    }
}
