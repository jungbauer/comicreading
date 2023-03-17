package com.comicreading.service;

import com.comicreading.domain.DatabaseLogs;
import com.comicreading.repository.DatabaseLogsRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public List<DatabaseLogs> getAllLogs() {
        List<DatabaseLogs> list = new ArrayList<>();
        for (DatabaseLogs log : databaseLogsRepository.findAll()) {
            list.add(log);
        }

        list.sort(Comparator.comparing(DatabaseLogs::getLogDateTime).reversed());

        return list;
    }

    public void deleteLog(Integer id) throws Exception{
        if(id == null) throw new Exception("Id should not be null");
        Optional<DatabaseLogs> optLog = databaseLogsRepository.findById(id);
        if(optLog.isPresent()) databaseLogsRepository.delete(optLog.get());
        else throw new Exception("log not found");
    }
}
