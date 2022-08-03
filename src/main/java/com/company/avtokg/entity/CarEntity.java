package com.company.avtokg.entity;

import com.company.avtokg.enums.car.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table
public class CarEntity extends BaseEntity {
    @Column
    private String weight;

    @Column
    private String model;

    @Column
    private String brand;


    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "region_id")
    private String regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", updatable = false,insertable = false)
    private RegionEntity region;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity image;

    @Column(name = "category_id")
    private String categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column
    private String engine; // двигатель

    @Column
    private LocalDate yearOfIssue;

    @Column(name = "generations")
    @OneToMany(fetch = FetchType.LAZY)
    private List<GenerationEntity> generationList;

    @Column
    @Enumerated(EnumType.STRING)
    private CarBodyType bodyType;

    @Column
    @Enumerated(EnumType.STRING)
    private CarBoxType carBoxType;

    @Column
    @Enumerated(EnumType.STRING)
    private DriveUnitType driveUnitType;

    @Column
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column
    @Enumerated(EnumType.STRING)
    private StateType stateType;

    @Column
    private Long priceSOM;

    @Column
    private Long priceUSD;

    @Column(name = "description_id")
    private String descriptionId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id", insertable = false, updatable = false)
    private DescriptionAndEquipmentEntity description;


    @Column
    @Enumerated(EnumType.STRING)
    private SteeringWheelType steeringWheel;// руль
}
