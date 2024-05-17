package org.prowikiq.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: RoleDto Project: prowikiQ Package: org.prowikiq.user.domain.dto
 * <p>
 * Description: RoleDto
 *
 * @author dong-hoshin
 * @date 5/17/24 17:00 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private Long roleId;
    private String roleName;
    private Long parentRoleId;
}
