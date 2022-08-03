package com.company.avtokg.service;

import com.company.avtokg.dto.request.ProfileChangeStatusRequestDTO;
import com.company.avtokg.dto.request.ProfileRequestDTO;
import com.company.avtokg.dto.response.ProfileResponseDTO;
import com.company.avtokg.entity.ProfileEntity;
import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.enums.ProfileStatus;
import com.company.avtokg.exception.ItemNotFoundException;
import com.company.avtokg.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;


    public ProfileResponseDTO create(ProfileRequestDTO requestDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(requestDTO.getName());
        entity.setSurname(requestDTO.getSurname());
        entity.setPhone(requestDTO.getSurname());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(ProfileRole.ROLE_USER);
        return toDTO(entity);
    }

    public ProfileResponseDTO getById(String id) {
        ProfileEntity entity = profileRepository.findById(id).orElseThrow(() -> {
            log.warn("Profile id not found");
            throw new ItemNotFoundException("Profile id not found");
        });
        return toDTO(entity);
    }


    public Boolean delete(String id) {
        profileRepository.deleteById(id);
        return true;
    }

    public Boolean changeStatus(String id, ProfileChangeStatusRequestDTO requestDTO) {
        int n = profileRepository.changeStatus(requestDTO.getStatus(), id);
        return n > 0;
    }

    private ProfileResponseDTO toDTO(ProfileEntity entity) {
        ProfileResponseDTO responseDTO = new ProfileResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setStatus(entity.getStatus());
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setPhone(entity.getPhone());
        responseDTO.setCreatedDate(entity.getCreatedDate());
        return responseDTO;
    }

    public ProfileEntity getByPhone(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new ItemNotFoundException("Not Found!"));
    }



    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            log.info("id not equal");
            throw new ItemNotFoundException("id not found");
        });
    }


    public Boolean updateDetail(String id, ProfileRequestDTO dto) {
        ProfileEntity entity = get(id);

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAttachId(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return true;
    }

    public Boolean updatePassword(String prePassword, String newPassword, String id) {
        ProfileEntity entity = get(id);
        if (!entity.getPassword().equals(prePassword)) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }
        entity.setPassword(newPassword);
        return true;
    }

    public List<ProfileResponseDTO> getPagination(int page, int size, String profileId) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        List<ProfileResponseDTO> dtoList = new LinkedList<>();

        profileRepository.findById(pageable, profileId).forEach(entity ->{
            dtoList.add(toDTO(entity));
        });
        if (dtoList.isEmpty()) {
            log.info("item not found");
            throw new ItemNotFoundException("Item not found");
        }
        return dtoList;
    }
}
