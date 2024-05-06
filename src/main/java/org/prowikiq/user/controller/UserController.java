package org.prowikiq.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.prowikiq.user.domain.dto.UserCreateDto;
import org.prowikiq.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: UserController Project: prowikiQ Package: org.prowikiq.user.controller
 * <p>
 * Description: UserController
 *
 * @author dong-hoshin
 * @date 4/23/24 21:48 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Api(tags = "User Controller")
@RestController
@RequestMapping("/account/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원 가입", notes = "아이디(휴대폰 번호), 비밀번호를 통해 회원가입 요청을 보냅니다.")
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserCreateDto userCreateDto) {
        userService.join(userCreateDto);
        return ResponseEntity.ok("join successfully");
    }

    @ApiOperation(value = "로그인", notes = "jwt 토큰을 사용하여 로그인시 토큰을 부여합니다.")
    @PostMapping("/login")
    public String login(@RequestBody UserCreateDto userCreateDto) {
        return "Bearer " + userService.login(userCreateDto);
    }

}
