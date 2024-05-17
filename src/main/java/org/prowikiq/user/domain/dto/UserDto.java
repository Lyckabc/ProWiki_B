package org.prowikiq.user.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: UserCreateDto Project: prowikiQ Package: org.prowikiq.user.domain.dto
 * <p>
 * Description: UserCreateDto
 *
 * @author dong-hoshin
 * @date 4/23/24 22:18 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String userPhoneNum;
    private String userPassword;
    private Long roleId;
    private String userPasswordHash;
    private String useremail;
    private String status;
    private String clazz;

}
