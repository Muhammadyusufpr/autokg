package com.company.avtokg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Column
    private String name;


}
