package org.example.oauthexam.service;

import lombok.RequiredArgsConstructor;
import org.example.oauthexam.entity.SocialLoginInfo;
import org.example.oauthexam.repository.SocialLoginInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialLoginInfoService {
    private final SocialLoginInfoRepository socialLoginInfoRepository;
    //    저장
    @Transactional
    public SocialLoginInfo saveSocialLoingInfo(String provider, String socialId){
        SocialLoginInfo socialLoginInfo = new SocialLoginInfo();
        socialLoginInfo.setProvider(provider);
        socialLoginInfo.setSocialId(socialId);
        return socialLoginInfoRepository.save(socialLoginInfo);
    }


    //    조회
    @Transactional(readOnly = true)
    public Optional<SocialLoginInfo> findByProdAndUuidAndSocialId(String provider, String uuid, String socialId){
        return socialLoginInfoRepository.findByProviderAndUuidAndSocialId(provider,uuid,socialId);
    }


}
