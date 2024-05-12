package org.prowikiq.user.service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.prowikiq.global.exception.impl.user.AlreadyExistUserException;
import org.prowikiq.global.exception.impl.user.NotExistUserException;
import org.prowikiq.global.exception.impl.user.PasswordNotMatchException;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.user.domain.dto.UserDto;
import org.prowikiq.user.domain.entity.User;
import org.prowikiq.user.domain.repository.UserRepository;
import org.prowikiq.wiki.domain.dto.WikiPageDto;
import org.prowikiq.wiki.domain.entity.WikiPage;
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
    public void join(UserDto userDto) {
        String userPhoneNum = userDto.getUserPhoneNum();

        // is there exist ID
        if (userRepository.existsByUserPhoneNum(userPhoneNum)) {
            throw new AlreadyExistUserException();
        }

        User user = User.builder()
            .userPhoneNum(userPhoneNum)
            .userPassword(passwordEncoder.encode(userDto.getUserPassword()))
            .build();

        userRepository.save(user);
    }

    @Value("${spring.jwt.secret}")
    private String secretKey;
    public String login(UserDto userDto) {
        User user = userRepository.findByUserPhoneNum(userDto.getUserPhoneNum())
            .orElseThrow(NotExistUserException::new);
        if (!passwordEncoder.matches(userDto.getUserPassword(), user.getUserPassword())) {
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

    @Transactional
    public User getUserFromId(Long userId) {
        Optional<User> wikiPage = userRepository.findByUserId(userId);

        return wikiPage.orElseThrow(() -> new NoSuchElementException("해당 pageId에 대한 저장 객체가 없습니다."));
    }

    @Transactional
    public UserDto userConvertToDto(User user) {

        UserDto dto =  UserDto.builder()
            .userPhoneNum(user.getUserPhoneNum())
            .userPassword(user.getUserPassword())
            .build();

        return dto;
    }
}
