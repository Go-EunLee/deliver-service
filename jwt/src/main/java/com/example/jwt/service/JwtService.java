package com.example.jwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
    private static String secretKey = "java11SpringBootJWTTokenIssueMethod";
    public String create(
            Map<String, Object> claims,
            LocalDateTime expireAt
    ){
        // HMAC : 해싱기법을 이용해서 메시지의 위변조가 있었는지 체크하는 기법
        // 해싱 : 원문 메시지를 일정한 길이의 다른 메시지로 변환하되, 일정 길이로 떨어지게 하는 기법
        // 다이제스트 : 원문을 해싱하여 나온 메시지
        // 공통 키와 내용을 HASH 알고리즘에 적용하여 시그니처를 생성

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());  // 키 생성
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());  // LocalDateTime -> java.util.Date
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setExpiration(_expireAt)
                .compact();
    }

    public void validation(String token){
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try{
            // 클레임 셋이 암호화 된 JWT -> JWE
            // 클레임 셋이 암호화 되지 않은 JWT -> JWS

            // parseClaimsJws 메서드는 기본적인 포맷을 검증하고, jwt를 생성할 때 사용했던 secretKey로 서명했을 때 토큰에 포함된
            // signature와 동일한 signature가 생성되는지 확인
            // 동일하지 않으면 에러 발생
            var result = parser.parseClaimsJws(token);
            result.getBody().entrySet().forEach(value -> {
                log.info("key : {}, value : {}", value.getKey(), value.getValue());
            });
        }catch (Exception e){
            if (e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception");
            }
            else if ( e instanceof ExpiredJwtException){
                throw new RuntimeException("JWT Token Expired Exception");
            }else{
                throw new RuntimeException("JWT Token Validation Exception");
            }
        }
    }
}
