package org.prowikiq.user.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.global.config.JwtTokenProvider;
import org.prowikiq.global.exception.impl.user.AlreadyExistUserException;
import org.prowikiq.global.exception.impl.user.NotExistRoleException;
import org.prowikiq.global.exception.impl.user.NotExistUserException;
import org.prowikiq.global.exception.impl.user.PasswordNotMatchException;
import org.prowikiq.user.domain.dto.UserSignDto;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.Role;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.RoleRepository;
import org.prowikiq.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Transactional
    public void join(UserSignDto userSignDto, String roleName) {
        String userPhoneNum = userSignDto.getUserPhoneNum();
        LocalDateTime now = LocalDateTime.now();
        // is there exist ID
        if (userRepository.existsByUserPhoneNum(userPhoneNum)) {
            throw new AlreadyExistUserException();
        }
        Role role = roleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
            .userPhoneNum(userPhoneNum)
            .userPassword(passwordEncoder.encode(userSignDto.getUserPassword()))
            .role(role)
            .build();

        userRepository.save(user);
    }


    public String login(UserSignDto userSignDto) {
        User user = userRepository.findByUserPhoneNum(userSignDto.getUserPhoneNum())
            .orElseThrow(NotExistUserException::new);
        if (!passwordEncoder.matches(userSignDto.getUserPassword(), user.getUserPassword())) {
            throw new PasswordNotMatchException();
        }
        if (user.getUserPhoneNum() == null) {
            throw new NotExistUserException();
        } else if (user.getRole() == null) {
            throw new NotExistRoleException();
        }

        return jwtTokenProvider.createToken(user.getUserPhoneNum(), user.getRole());
    }

    public void deleteUser(UserSignDto userSignDto) {
        String userPhoneNum = userSignDto.getUserPhoneNum();
        String password = userSignDto.getUserPassword();
        Optional<User> optionalUser = userRepository.findByUserPhoneNum(userPhoneNum);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getUserPassword())) {
                userRepository.delete(user);
            } else {
                throw new PasswordNotMatchException();
            }
        } else {
            throw new NotExistUserException();
        }
    }

    @Transactional
    public User getUserFromId(Long userId) {
        Optional<User> wikiPage = userRepository.findByUserId(userId);

        return wikiPage.orElseThrow(() -> new NoSuchElementException("해당 pageId에 대한 저장 객체가 없습니다."));
    }


    @Transactional
    public UserDto userConvertToDto(User user) {

        UserDto dto =  UserDto.builder()
            .userId(user.getUserId())
            .userPhoneNum(user.getUserPhoneNum())
            .userPassword(user.getUserPassword())
            .build();

        return dto;
    }
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getFromUserPhoneNum(String userPhoneNum) {
        User user = userRepository.findByUserPhoneNum(userPhoneNum)
            .orElseThrow(() -> new RuntimeException("There is no userPhoneNum"));

        return user;
    }
}
