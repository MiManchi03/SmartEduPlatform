package com.llf.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtUtils {

    // 使用和测试类一致的 Base64 编码密钥
    private static final String SECRET_KEY = "bGxmYWlzcw==";

    // 过期时间 12 小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 12;

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义载荷数据（建议至少包含用户身份标识）
     * @return JWT 令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定签名算法和密钥
                .addClaims(claims)                             // 添加自定义数据
                .setIssuedAt(new Date())                       // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .compact();                                    // 生成令牌字符串
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 令牌字符串
     * @return 解析后的载荷数据
     * @throws io.jsonwebtoken.JwtException 如果令牌无效/过期/被篡改
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)    // 设置验签密钥
                .parseClaimsJws(token)        // 解析令牌
                .getBody();                   // 获取载荷数据
    }
}