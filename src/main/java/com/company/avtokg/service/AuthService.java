package com.company.avtokg.service;

import com.company.avtokg.dto.AuthDTO;
import com.company.avtokg.dto.RegistrationDto;
import com.company.avtokg.dto.response.ProfileResponseDTO;
import com.company.avtokg.entity.ProfileEntity;
import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.enums.ProfileStatus;
import com.company.avtokg.exception.AppBadRequestException;
import com.company.avtokg.exception.AppForbiddenException;
import com.company.avtokg.exception.ItemAlreadyExistsException;
import com.company.avtokg.exception.ItemNotFoundException;
import com.company.avtokg.repository.ProfileRepository;
import com.company.avtokg.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;


    public void registration(RegistrationDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optional.isPresent()) {
            log.warn("email already exists : {}", dto);
            throw new ItemAlreadyExistsException("email already exists!");
        }

        ProfileEntity entity = toProfileEntity(dto);
            profileRepository.save(entity);
    }



    public ProfileEntity toProfileEntity(RegistrationDto dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public ProfileResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (!optional.isPresent()) {
            log.warn("email or password wrong");
            throw new AppForbiddenException("email or password wrong");
        }

        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            log.info("not access");
            throw new AppForbiddenException("not access");
        }

        ProfileResponseDTO responseDTO = new ProfileResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setPhone(entity.getPhone());
        responseDTO.setJwt(JwtUtil.encode(entity.getId()));
        return responseDTO;
    }

}
