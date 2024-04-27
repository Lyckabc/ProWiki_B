package org.prowikiq.user.service;

import java.util.Collections;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.global.exception.impl.user.AlreadyExistUserException;
import org.prowikiq.user.domain.dto.UserCreateDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class: UserService Project: prowikiQ Package: org.prowikiq.user.service
 * <p>
 * Description: UserService
 *
 * @author dong-hoshin
 * @date 4/23/24 21:48 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserCreateDto userCreateDto) {
        String userPhoneNum = userCreateDto.getUserPhoneNum();

        // is there exist ID
        if (userRepository.existsByUserPhoneNum(userPhoneNum)) {
            throw new AlreadyExistUserException();
        }

        User user = User.builder()
            .userPhoneNum(userPhoneNum)
            .userPassword(passwordEncoder.encode(userCreateDto.getUserPassword()))
            .build();

        userRepository.save(user);
    }
}
