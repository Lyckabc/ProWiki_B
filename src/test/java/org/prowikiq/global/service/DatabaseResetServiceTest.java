package org.prowikiq.global.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Class: DatabaseResetServiceTest Project: prowikiQ Package: org.prowikiq.global.service
 * <p>
 * Description:
 *
 * @author dong-hoshin
 * @date 5/6/24 04:03 Copyright (c) 2024
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DatabaseResetServiceTest {

    @InjectMocks
    private DatabaseResetService resetService;

    @Mock
    private DatabaseResetService databaseResetService; // Example dependency

    @Test
    @DisplayName("DatabaseResetServiceTest success")
    void resetDatabaseTest() {
        databaseResetService.resetDatabase();
        verify(databaseResetService, times(1)).resetDatabase(); // Verify that the cleanDatabase method was called once
    }
}