package com.company.avtokg.service;

import com.company.avtokg.dto.*;
import com.company.avtokg.entity.EntityDetails;
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
public class CarService  {
    private final CarRepository carRepository;
    private final DescriptionAndEquipmentService descriptionAndEquipmentService;
    private final GenerationService generationService;


    public CarDTO create(CarFullDTO dto, String attachId) {
        CarEntity entity = new CarEntity();

        entity.setEngine(dto.getCarDTO().getEngine());
        entity.setWeight(dto.getCarDTO().getWeight());
        entity.setYearOfIssue(dto.getCarDTO().getYearOfIssue());
        entity.setModel(dto.getCarDTO().getModel());
        entity.setBrand(dto.getCarDTO().getBrand());
        entity.setFuelType(dto.getCarDTO().getFuelType());
        entity.setSteeringWheel(dto.getCarDTO().getSteeringWheel());
        entity.setStateType(dto.getCarDTO().getStateType());
        entity.setBodyType(dto.getCarDTO().getCarBodyType());
        entity.setDriveUnitType(dto.getCarDTO().getDriveUnitType());
        entity.setProfileId((EntityDetails.getProfile().getId()));
        entity.setAttachId(attachId);
        entity.setPriceSOM(dto.getCarDTO().getPriceSOM());
        entity.setPriceUSD(dto.getCarDTO().getPriceUSD());
        entity.setRegionId(dto.getCarDTO().getRegionId());

        carRepository.save(entity);

        generationService.create(dto.getGenerationDTO(), entity.getId());

        descriptionAndEquipmentService.create(entity.getId(), dto.getEquipmentDTO());

        return new CarDTO(entity.getId(),entity.getCreatedDate());
    }


    public Boolean update(String id, CarDTO dto) {
        CarEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found {}", id);
            throw new ItemNotFoundException("id not found");
        }

        entity.setImage(dto.getImage());
        entity.setModel(dto.getModel());
        entity.setWeight(dto.getWeight());
        entity.setEngine(dto.getEngine());
        entity.setYearOfIssue(dto.getYearOfIssue());
        return true;
    }


    public CarEntity get(String id) {
        return carRepository.findById(id).orElseThrow(() -> {
            log.info("id not equal");
            throw new ItemNotFoundException("id not found");
        });
    }

    public List<CarDTO> getById(String id, int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        List<CarDTO> dtoList = new LinkedList<>();
        carRepository.findByProfileId(pageable, id).forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        if (dtoList.isEmpty()) {
            log.warn("Item not found");
            throw new ItemNotFoundException("Item not found");
        }
        return dtoList;
    }


    public ToFullInfoDTO toFullInfoDTO(String id ) {
        ToFullInfoDTO dto = new ToFullInfoDTO();
        CarMapper mapper = carRepository.getCar(id).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });

        dto.setYearOfIssue(mapper.getC_yearOfIssue());
        dto.setMileage(mapper.getD_mileage());
        dto.setCarBodyType(mapper.getC_bodyType());
        dto.setColor(mapper.getD_color());
        dto.setEngine(mapper.getC_engine());
        dto.setCarBoxType(mapper.getC_carBoxType());
        dto.setDriveUnitType(mapper.getC_deriveUtilType());
        dto.setSteeringWheelType(mapper.getC_steeringWheel());
        dto.setStateType(mapper.getC_stateType());
        dto.setCustomsClearedType(mapper.getD_clearedType());
        dto.setOther(mapper.getD_description());
        dto.setFuelType(mapper.getC_fuelType());
        dto.setDto(new RegionDTO(mapper.getR_id(), mapper.getR_key()));
        return dto;
    }



    public CarDTO toDTO(CarEntity entity) {
        CarDTO dto = new CarDTO();

        dto.setEngine(entity.getEngine());
        dto.setImage(entity.getImage());
        dto.setModel(entity.getModel());
        dto.setWeight(entity.getWeight());
        dto.setPriceSOM(entity.getPriceSOM());
        dto.setPriceUSD(entity.getPriceUSD());
        dto.setYearOfIssue(entity.getYearOfIssue());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CarDTO getBYId(String id) {
        CarEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }

        return toDTOById(entity);
    }

    public CarDTO toDTOById(CarEntity entity) {
        CarDTO dto = new CarDTO();

        dto.setRegionId(entity.getRegionId());
        dto.setPriceUSD(entity.getPriceUSD());
        dto.setPriceSOM(entity.getPriceSOM());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setAttachId(entity.getAttachId());
        dto.setYearOfIssue(entity.getYearOfIssue());

        return dto;
    }



    public Boolean delete(String id) {
        CarEntity entity = get(id);
        if (!entity.getId().equals(id)) {
            log.info("id not found");
            throw new ItemNotFoundException("id not found");
        }
        carRepository.deleteById(id);
        return true;
    }
    public List<CarDTO> getPaginationByCategoryId(int size, int page, String categoryId) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        List<CarDTO> dtoList = new LinkedList<>();
        carRepository.findByCategoryId(pageable, categoryId).forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        if (dtoList.isEmpty()) {
            log.warn("Item not found");
            throw new ItemNotFoundException("Item not found");
        }
        return dtoList;
    }

    public List<CarDTO> getPaginationByRegionId(int size, int page, String id) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        List<CarDTO> dtoList = new LinkedList<>();
        carRepository.findByRegionId(pageable,id).forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        if (dtoList.isEmpty()) {
            log.info("Item not found");
            throw new ItemNotFoundException("Item not found");
        }
        return dtoList;
    }


}
