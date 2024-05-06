package org.prowikiq.global.controller;

import org.prowikiq.global.service.DatabaseResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: DatabaseResetController Project: prowikiQ Package: org.prowikiq.global.controller
 * <p>
 * Description: DatabaseResetController
 *
 * @author dong-hoshin
 * @date 5/6/24 04:05 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@RestController
@RequestMapping("/api/reset")
public class DatabaseResetController {
    private final DatabaseResetService resetService;

    @Autowired
    public DatabaseResetController(DatabaseResetService resetService) {
        this.resetService = resetService;
    }

    @PostMapping
    public ResponseEntity<String> resetDatabase() {
        resetService.resetDatabase();
        return ResponseEntity.ok("Database reset successfully");
    }

}
