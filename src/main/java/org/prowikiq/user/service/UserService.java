package org.prowikiq.user.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.global.exception.impl.user.AlreadyExistUserException;
import org.prowikiq.global.exception.impl.user.NotExistUserException;
import org.prowikiq.global.exception.impl.user.PasswordNotMatchException;
import org.prowikiq.user.domain.dto.UserSignDto;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
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

    @Transactional
    public void join(UserSignDto userSignDto) {
        String userPhoneNum = userSignDto.getUserPhoneNum();
        LocalDateTime now = LocalDateTime.now();
        // is there exist ID
        if (userRepository.existsByUserPhoneNum(userPhoneNum)) {
            throw new AlreadyExistUserException();
        }

        User user = User.builder()
            .userPhoneNum(userPhoneNum)
            .userPassword(passwordEncoder.encode(userSignDto.getUserPassword()))
            .createdAt(now)
            .modifiedAt(now)
            .latestedAt(now)
            .build();

        userRepository.save(user);
    }

    @Value("${spring.jwt.secret}")
    private String secretKey;
    public String login(UserSignDto userSignDto) {
        User user = userRepository.findByUserPhoneNum(userSignDto.getUserPhoneNum())
            .orElseThrow(NotExistUserException::new);
        if (!passwordEncoder.matches(userSignDto.getUserPassword(), user.getUserPassword())) {
            throw new PasswordNotMatchException();
        }

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);


        String token = Jwts.builder()
            .setSubject(user.getUserPhoneNum())
            .setIssuedAt(new Date(nowMillis))
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

        return token;
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
}
