package com.company.avtokg.entity;

import com.company.avtokg.config.CustomProfileDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class EntityDetails {
    public static ProfileEntity getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomProfileDetails details = (CustomProfileDetails) authentication.getPrincipal();
        return details.getProfile();
    }


}
