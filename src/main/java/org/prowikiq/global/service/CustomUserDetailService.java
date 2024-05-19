package org.prowikiq.global.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.prowikiq.user.domain.entity.CustomUserDetails;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class: CustomUserDetailService Project: prowikiQ Package: org.prowikiq.global.service
 * <p>
 * Description: CustomUserDetailService
 *
 * @author dong-hoshin
 * @date 5/18/24 10:59 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = null;
    }

    @Override
    public UserDetails loadUserByUsername(String userPhoneNum) throws UsernameNotFoundException {
        User user = userService.getFromUserPhoneNum(userPhoneNum);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + userPhoneNum);
        }
        return new CustomUserDetails(user);
    }


}