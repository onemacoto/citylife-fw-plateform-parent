package com.citylife.common.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import com.citylife.common.model.IUser;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

  private final static Key secretKey;

  static {
    secretKey = serializeKey("secret");
  }

  public static String createToken(IUser user) {
    Map<String, Object> claims = BeanMapper.asMap(user);
    JwtBuilder builder = Jwts.builder().setHeaderParam("alg", "HS512").setHeaderParam("typ", "JWT").addClaims(claims)
        .signWith(SignatureAlgorithm.HS512, secretKey);
    return builder.compact();
  }

  public static <T extends IUser> IUser parseToken(String token, Class<T> clazz) {
    
    Map<String, Object> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    return BeanMapper.asBean(claims, clazz);
  }
  

  private static Key serializeKey(String encodedKey) {
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    Key key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
    return key;
  }
}
