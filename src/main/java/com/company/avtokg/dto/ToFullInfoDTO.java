package com.company.avtokg.dto;

import com.company.avtokg.enums.car.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ToFullInfoDTO {
    private String id;
    private LocalDate YearOfIssue;
    private Integer mileage;
    private String color;
    private String engine;
    private CarBodyType carBodyType;
    private CarBoxType carBoxType;
    private DriveUnitType driveUnitType;
    private SteeringWheelType steeringWheelType;
    private StateType stateType;
    private CustomsClearedType customsClearedType;
    private RegionDTO dto;
    private String other;
    private FuelType fuelType;

    /*
Год выпуска
Пробег
Кузов
Цвет
Двигатель
Коробка
Привод
Руль
Состояние
Таможня
Наличие
Регион
Прочее
     */


}
