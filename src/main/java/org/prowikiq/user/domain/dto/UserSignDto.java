package org.prowikiq.user.domain.dto;

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
 * @date 5/13/24 22:01 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignDto {
    private String userPhoneNum;//유저 휴대폰 번호 겸 아이디
    private String userPassword;//유저 비밀번호

}
