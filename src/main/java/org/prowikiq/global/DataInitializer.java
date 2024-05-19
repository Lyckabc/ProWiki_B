package org.prowikiq.global;

import lombok.RequiredArgsConstructor;
import org.prowikiq.user.domain.entity.Role;
import org.prowikiq.user.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class: DataInitializer Project: prowikiQ Package: org.prowikiq.global
 * <p>
 * Description: DataInitializer
 *
 * @author dong-hoshin
 * @date 5/17/24 17:29 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */

/*@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
        Role rootRole = roleService.makeRole("Root", null);
        Role projectManagerRole = roleService.makeRole("ProjectManager", rootRole.getRoleId());
        Role teamManagerRole = roleService.makeRole("TeamManager", projectManagerRole.getRoleId());
        Role memberRole = roleService.makeRole("Member", teamManagerRole.getRoleId());
    }
}*/
