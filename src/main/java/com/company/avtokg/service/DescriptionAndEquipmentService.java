package com.company.avtokg.service;

import com.company.avtokg.dto.DescriptionAndEquipmentDTO;
import com.company.avtokg.entity.CarEntity;
import com.company.avtokg.entity.DescriptionAndEquipmentEntity;
import com.company.avtokg.exception.ItemNotFoundException;
import com.company.avtokg.repository.DescriptionAndEquipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DescriptionAndEquipmentService {


    private final DescriptionAndEquipmentRepository descriptionAndEquipmentRepository;
    @Lazy
    @Autowired
    private CarService carService;

    public DescriptionAndEquipmentDTO create(String carId, DescriptionAndEquipmentDTO dto) {
        CarEntity car = carService.get(carId);
        DescriptionAndEquipmentEntity entity = new DescriptionAndEquipmentEntity();

        if (!car.getId().equals(entity.getCarId())) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }

        entity.setDescription(dto.getDescription());
        entity.setCarId(car.getId());
        entity.setColor(dto.getColor());
        entity.setMileage(dto.getMileage());
        entity.setStateType(dto.getStateType());
        descriptionAndEquipmentRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }


    public Boolean update(String carId, DescriptionAndEquipmentDTO dto) {
        CarEntity carEntity = carService.get(carId);

        DescriptionAndEquipmentEntity entity = new DescriptionAndEquipmentEntity();
        if (!carEntity.getId().equals(entity.getCarId())) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }

        entity.setStateType(dto.getStateType());
        entity.setDescription(dto.getDescription());
        entity.setMileage(dto.getMileage());
        entity.setColor(dto.getColor());
        entity.setCarId(carEntity.getId());
        entity.setClearedType(dto.getClearedType());
        return true;
    }

    public DescriptionAndEquipmentDTO toDTO(DescriptionAndEquipmentEntity entity) {
        DescriptionAndEquipmentDTO dto = new DescriptionAndEquipmentDTO();

        dto.setDescription(entity.getDescription());
        dto.setCarId(entity.getCarId());
        dto.setMileage(entity.getMileage());
        dto.setClearedType(entity.getClearedType());
        dto.setColor(entity.getColor());
        dto.setStateType(entity.getStateType());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean delete(String id) {
        DescriptionAndEquipmentEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }
        descriptionAndEquipmentRepository.deleteById(id);
        return true;
    }

    public DescriptionAndEquipmentDTO getById(String id) {
        DescriptionAndEquipmentEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found {}", id);
            throw new ItemNotFoundException("id not found");
        }
        return toDTO(entity);
    }



    public DescriptionAndEquipmentEntity get(String id) {
        return descriptionAndEquipmentRepository.findById(id).orElseThrow(() ->{
            log.info("id not equal");
            throw new ItemNotFoundException("id not found");
        });
    }

}
