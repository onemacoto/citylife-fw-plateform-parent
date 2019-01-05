package com.citylife.common.component;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import com.citylife.common.model.IHeaderUser;
import com.citylife.common.model.UserValueObject;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTHelper {

  private Key secretKey;

  @Autowired
  private BeanMapper mapper;

  @PostConstruct
  public void init() {
    secretKey = serializeKey("secret");
  }

  public String createAdminToken() {
    IHeaderUser user = UserValueObject.empty();
    user.setUserId(0L);
    return createToken(user);
  }

  public String createToken(IHeaderUser user) {
    Map<String, Object> claims = mapper.asMap(user);
    JwtBuilder builder = Jwts.builder().setHeaderParam("alg", "HS512").setHeaderParam("typ", "JWT").addClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey);
    return builder.compact();
  }

  public <T extends IHeaderUser> IHeaderUser parseToken(String token, Class<T> clazz) {

    Map<String, Object> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    return mapper.asBean(claims, clazz);
  }

  private Key serializeKey(String encodedKey) {
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    Key key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
    return key;
  }
}
