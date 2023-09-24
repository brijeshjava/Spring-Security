package com.jwt.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.security.entity.RefreshToken;
import com.jwt.security.repository.RefreshTokenRepository;
import com.jwt.security.repository.UserInfoRepository;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class RefreshTokenService {

    
    private final RefreshTokenRepository refreshTokenRepository;

    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;

    public RefreshToken createRefreshToken(String username) {
    	Integer id=userInfoRepository.findByName(username).get().getId();
    	RefreshToken refreshToken=refreshTokenRepository.findByUserInfoId(id).orElse(null);
    	if(refreshToken==null) {
            refreshToken = RefreshToken.builder()
                .userInfo(userInfoRepository.findByName(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))//10
                .build();
         refreshTokenRepository.save(refreshToken);
    	}
      return  refreshToken ;
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}
