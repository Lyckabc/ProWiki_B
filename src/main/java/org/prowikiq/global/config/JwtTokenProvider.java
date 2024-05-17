package org.prowikiq.global.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.prowikiq.user.domain.entity.Role;
import org.prowikiq.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Class: JwtTokenProvider Project: prowikiQ Package: org.prowikiq.global.config
 * <p>
 * Description: JwtTokenProvider
 *
 * @author dong-hoshin
 * @date 5/17/24 17:40 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${spring.jwt.secret}")
    private String secretKey;

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    @Value("${spring.jwt.time}")
    private long tokenValidTime;
    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    private final UserDetailsService userDetailsService;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk, Role role) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("role", role.getRoleName()); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims) // 정보 저장
            .setIssuedAt(now) // 토큰 발행 시간 정보
            .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
            .signWith(SignatureAlgorithm.HS512, secretKey)  // 사용할 암호화 알고리즘과
            // signature 에 들어갈 secret값 세팅
            .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "Bearer +TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader((TOKEN_HEADER));

        if (!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) { //토큰형태 포함
            return token.substring(TOKEN_PREFIX.length()); //실제토큰 부위
        }

        return null;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //권한이 Root인지 확인 하는 기능
    public boolean userHasRootRole(String token) {
        if (token != null && validateToken(token)) {
            String userPk = getUserPk(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userPk);

            return userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("Root"));
        }
        return false;
    }

    //권한이 Manager인지 확인 하는 기능
    public boolean userHasManagerRole(String token) {
        if (token != null && validateToken(token)) {
            String userPk = getUserPk(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userPk);

            return userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().contains("Manager"));
        }
        return false;
    }

    //권한이 Member인지 확인 하는 기능
    public boolean userHasMemberRole(String token) {
        if (token != null && validateToken(token)) {
            String userPk = getUserPk(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userPk);
            return userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("Member"));
        }
        return false;
    }

    // 토큰에서 회원 정보 추출
    public String getUserPhoneNum(String token) {
        String userPk = getUserPk(token); // 토큰에서 userPk 추출
        // userPk를 사용하여 userPhoneNum 찾기
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPk);
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            return user.getUserPhoneNum(); // userPhoneNum 반환
        }
        return null;
    }
}
