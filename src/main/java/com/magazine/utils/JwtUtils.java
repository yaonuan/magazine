package com.magazine.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.magazine.domain.UserEntity;

import java.util.Date;

/**
 * jwt工具类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-16
 */
public class JwtUtils {

    public static final String SUBJECT = "xiaoxuan";

    public static final long EXPIRE = 1000*60*60*24; //过去时间,毫秒,一天

    //秘钥
    public static final String APPSECRET = "xiaomo666";


    /**
     * 生成jwt
     * @param user
     * @return
     */
    public static String geneJsonWebToken(UserEntity user){
        if (user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null){
            return null;
        }else {
            String token = Jwts.builder().setSubject(SUBJECT).claim("id",user.getId())
                    .claim("name",user.getName())
//                    .claim("img", user.getHeadImg())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+ EXPIRE))
                    .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
            return  token;
        }
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        try {
            final Claims claims =  Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return  claims;
        } catch (Exception e) { }
        return null;
    }
}
