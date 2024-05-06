package org.prowikiq.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class: HomeController Project: prowikiQ Package: org.prowikiq.home.controller
 * <p>
 * Description: HomeController
 *
 * @author dong-hoshin
 * @date 5/6/24 04:50 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";  // Ensure that "index" template exists or change this to return a valid view name
    }

}
