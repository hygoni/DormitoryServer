package com.github.hygoni.dormitory.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("spring.jwt.secret")
    private String secretKey;

    private long tokenValidMilliseconds = 1000L * 60 * 60; //토큰은 1시간 동안 유효

    private final UserDetailsService userDetailsService;

    //secretKey를 base64 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString((secretKey.getBytes()));
    }

    //Jwt 인증 토큰 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles); //사용자 roles (권한) 설정
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) //발행 시간 설정
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds)) //유효 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //Jwt 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //RequestHeader에서 token 가져옴
    public String resolveToken(HttpServletRequest req){
        return req.getHeader("X-AUTH_TOKEN");
    }

    //Jwt 토큰에서 회원 고유 정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //토큰 유효성 검사
    public boolean isValidToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
