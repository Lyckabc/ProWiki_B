package org.prowikiq.global.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.prowikiq.global.service.DatabaseResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Class: DatabaseResetControllerTest Project: prowikiQ Package: org.prowikiq.global.controller
 * <p>
 * Description: DatabaseResetControllerTest
 *
 * @author dong-hoshin
 * @date 5/6/24 04:09 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(DatabaseResetController.class)
public class DatabaseResetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseResetService resetService;

    @Test
    @DisplayName("DatabaseResetControllerTest success")
    public void resetDatabaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/reset")
                .contentType("application/json"))
            .andExpect(status().isOk());
    }
}