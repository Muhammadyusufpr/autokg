package com.company.avtokg.service;

import com.company.avtokg.dto.RegionDTO;
import com.company.avtokg.entity.ProfileEntity;
import com.company.avtokg.entity.RegionEntity;
import com.company.avtokg.enums.LangEnum;
import com.company.avtokg.exception.ItemNotFoundException;
import com.company.avtokg.exception.RegionAlreadyExistsException;
import com.company.avtokg.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    private final ProfileService profileService;

    public RegionDTO create(RegionDTO dto, String pId) {
        ProfileEntity profileEntity = profileService.get(pId);

        Optional<RegionEntity> optional = regionRepository.findByKey(dto.getKey());
        if (optional.isPresent()) {
            throw new RegionAlreadyExistsException("This Region already used!");
        }

        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setKey(dto.getKey());
        entity.setProfile(profileEntity);

        regionRepository.save(entity);
        return toDTO(entity);
    }

    public List<RegionDTO> list() {
        List<RegionDTO> list = new ArrayList<>();
        regionRepository.findAllByVisible(true).forEach(entity -> {
            list.add(toDTO(entity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("Not Found!");
        }
        return list;
    }

    public List<RegionDTO> list(LangEnum lang) {
        List<RegionEntity> entityList = regionRepository.findAllByVisible(true);
        List<RegionDTO> dtoList = new ArrayList<>();

        for (RegionEntity entity : entityList) {
            RegionDTO dto = new RegionDTO();
            dto.setKey(entity.getKey());
            dto.setId(entity.getId());

            switch (lang) {
                case uz:
                    dto.setName(entity.getNameUz());
                    break;
                case en:
                    dto.setName(entity.getNameEn());
                    break;
                case ru:
                    dto.setName(entity.getNameRu());
                    break;
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    public RegionDTO update(String id, RegionDTO dto) {
        ProfileEntity profileEntity = profileService.get(dto.getProfileId());

        RegionEntity entity = regionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Not Found!"));

        if (!entity.getVisible()) {
            throw new ItemNotFoundException("Not Found!");
        }

        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setKey(dto.getKey());
        regionRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean delete(String id) {
        RegionEntity entity = regionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Not Found!"));

        if (!entity.getVisible()) {
            throw new ItemNotFoundException("Not Found!");
        }

        int n = regionRepository.updateVisible(false, id);
        return n > 0;
    }

    private RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setKey(entity.getKey());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public  RegionDTO getById(String id, LangEnum langEnum){
//        RegionEntity regionEntity = regionRepository.findById(id);
//        return toDTO(regionEntity);
        return null;
    }
}
