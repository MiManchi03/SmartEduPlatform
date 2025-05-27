package com.llf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /*生成jwt令牌-Jwts.builder()*/
    @Test
    public void testJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "llfaiss");
        /*将“llfaiss”通过base64算法加密后传递过去*/
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "bGxmYWlzcw==")
                .addClaims(dataMap)//添加自定义信息
                /*创建时间对象，并通过System.currentTimeMillis()设置为当前系统时间，
                再在此基础上加1000 * 60 * 60 * 24毫秒，即24小时后过期，因为单位是毫秒所以结尾要×1000*/
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//指定有效期时间
                .compact();//生成jwt令牌
        //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJsbGZhaXNzIiwiZXhwIjoxNzQ1NzM3MDI3fQ.ZxS-CuaqrnvZI--_qq27NoXATqfPrz3zQbDUt3nZC0k
        System.out.println(jwt);
    }

    /*解析jwt令牌-Jwts.parser()*/
    @Test
    public void testJwt1() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJsbGZhaXNzIiwiZXhwIjoxNzQ1NzM2NDI5fQ.NsPqCG0w6_axLg95M37HWKtMCynfp5zJP2GxGFCUcpQ";
        Claims body = Jwts.parser().setSigningKey("bGxmYWlzcw==")//设置加密算法
                .parseClaimsJws(token)//解析jwt令牌
                .getBody();//获取jwt令牌中的数据
        System.out.println(body);//{id=1, username=llfaiss, exp=1745736429}
    }
}
