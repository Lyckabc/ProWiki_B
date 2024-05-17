package org.prowikiq.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.prowikiq.user.domain.entity.Role;
import org.prowikiq.user.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: RoleController Project: prowikiQ Package: org.prowikiq.user.controller
 * <p>
 * Description: RoleController
 *
 * @author dong-hoshin
 * @date 5/17/24 17:25 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/acount/roles")
public class RoleController {
    private final RoleService roleService;

    @ApiOperation(value = "역할 만들기", notes = "역할 이름과 parentId 참고")
    @PostMapping("/")
    public ResponseEntity<Role> makeRole(@RequestParam String roleName, @RequestParam(required = false) Long parentRoleId) {
        Role role = roleService.makeRole(roleName, parentRoleId);
        return ResponseEntity.ok(role);
    }
}
