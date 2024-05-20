package org.prowikiq;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

/**
 * Class: prowikiqApplicationTest Project: prowikiQ Package: org.prowikiq
 * <p>
 * Description:
 *
 * @author dong-hoshin
 * @date 5/6/24 05:16 Copyright (c) 2024
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableJpaAuditing
@EnableConfigurationProperties
class ProwikiqApplicationTest {
    /*@Test
    void main() {

    }*/
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testServerIsUp() {
        // Example test to verify server is running and responsive
        String url = "http://localhost:" + port + "/";
        String response = restTemplate.getForObject(url, String.class);
        assertNotNull(response);
    }
    /*@Test
    void applicationContextLoads() {
        // Try to start the application context and catch any exceptions that occur
        assertDoesNotThrow(() -> {
            try (ConfigurableApplicationContext context = SpringApplication.run(prowikiqApplication.class)) {
                assertNotNull(context, "Application context should not be null");
                assertTrue(context.isActive(), "Application context should be active");
            }
        }, "The application should start without throwing any exceptions");
    }*/


}