package org.prowikiq.global.controller;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Class: DatabaseServerTest Project: prowikiQ Package: org.prowikiq.global.controller
 * <p>
 * Description: DatabaseServerTest
 *
 * @author dong-hoshin
 * @date 5/6/24 06:48 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
public class DatabaseServerTest {
    private static Server webServer;

    @BeforeAll
    public static void startServer() throws SQLException {
        // Start the H2 web server on port 8082
        webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        webServer.start();
    }

    @AfterAll
    public static void stopServer() throws SQLException {
        if (webServer != null) {
            webServer.stop();
        }
    }

}
