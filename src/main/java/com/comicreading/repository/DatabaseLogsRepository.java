package com.comicreading.repository;

import com.comicreading.domain.DatabaseLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseLogsRepository extends JpaRepository<DatabaseLogs, Integer> {
}
