package jwtexam.service;

import jwtexam.domain.RefreshToken;
import jwtexam.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    //저장
    @Transactional
    public RefreshToken addRefreshToken(RefreshToken refreshToken){
        return refreshTokenRepository.save(refreshToken);
    }

    //조회
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findRefreshToken(String refreshToken){
        return refreshTokenRepository.findByToken(refreshToken);
    }


    //삭제
    @Transactional
    public void deleteRefreshToken(String refreshToken){
        refreshTokenRepository.findByToken(refreshToken)
                .ifPresent(refreshTokenRepository::delete);
    }


}
