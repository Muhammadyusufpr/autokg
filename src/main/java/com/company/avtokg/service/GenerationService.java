package com.company.avtokg.service;

import com.company.avtokg.dto.GenerationDTO;
import com.company.avtokg.entity.GenerationEntity;
import com.company.avtokg.exception.ItemNotFoundException;
import com.company.avtokg.repository.GenerationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerationService {
    private final GenerationRepository generationRepository;


    public GenerationDTO create(GenerationDTO dto, String carId) {
        GenerationEntity entity = getByCarId(carId);
        if (!entity.getId().equals(carId)) {
            log.info("id not found {}", carId);
            throw new ItemNotFoundException("id not found");
        }


        entity.setAttachId(dto.getAttachId());
        entity.setCarId(carId);
        entity.setDate(dto.getDate());
        entity.setModel(dto.getModel());
        generationRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


    public GenerationDTO update(String carId, GenerationDTO dto) {
        GenerationEntity entity = getByCarId(carId);
        if (!entity.getId().equals(carId)) {
            log.info("id not found {}", carId);
            throw new ItemNotFoundException("id not found");
        }

        entity.setModel(dto.getModel());
        entity.setAttachId(dto.getAttachId());
        entity.setDate(dto.getDate());
        return toDTO(entity);
    }


    public GenerationEntity get(String id) {
        return generationRepository.findById(id).orElseThrow(() ->{
            log.info("id not equal");
            throw new ItemNotFoundException("id not found");
        });
    }

    public GenerationDTO toDTO(GenerationEntity entity) {
        GenerationDTO dto = new GenerationDTO();

        dto.setModel(entity.getModel());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCarId(entity.getCarId());
        dto.setAttachId(entity.getAttachId());
        dto.setDate(entity.getDate());
        return dto;
    }


    public GenerationEntity getByCarId(String carId) {
        Optional<GenerationEntity> optional = generationRepository.findByCarId(carId);
        if (optional.isPresent()) {
            log.info("car id not found: {}", carId);
            throw new ItemNotFoundException("car id not found");
        }
        return optional.get();
    }


    public GenerationDTO getById(String id) {
        GenerationEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found {}", id);
            throw new ItemNotFoundException("id not found");
        }
        return toDTO(entity);
    }


    public Boolean delete(String id) {
        GenerationEntity entity = getByCarId(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found {}", id);
            throw new ItemNotFoundException("id not found");
        }
        generationRepository.deleteById(id);
        return true;
    }

}
