package com.company.avtokg.service;

import com.company.avtokg.config.CustomProfileDetails;
import com.company.avtokg.entity.ProfileEntity;
import com.company.avtokg.enums.ProfileStatus;
import com.company.avtokg.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public CustomProfileDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        ProfileEntity entity = profileRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Profile Not Found"));

        if (entity.getStatus().equals(ProfileStatus.BLOCK)) {
            log.warn("Profile Blocked id={}", id);
            throw new BadCredentialsException("Profile Blocked!, please contact us");
        }

        return new CustomProfileDetails(entity);
    }
}
