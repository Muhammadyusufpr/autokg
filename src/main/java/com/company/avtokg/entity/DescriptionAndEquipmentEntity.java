package com.company.avtokg.entity;

import com.company.avtokg.enums.car.CustomsClearedType;
import com.company.avtokg.enums.car.StateType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "description")
public class DescriptionAndEquipmentEntity extends BaseEntity {
    @Column
    private String color;

    @Column
    @Enumerated(EnumType.STRING)
    private StateType stateType;

    @Column
    private String mileage; // пробег

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private CustomsClearedType clearedType; // растаможен

    @Column(name = "car_id")
    private String carId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", insertable = false, updatable = false)
    private CarEntity car;
}
