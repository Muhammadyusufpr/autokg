package com.company.avtokg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id; // uuid
    @Column
    private String path;
    @Column
    private String extension;
    @Column()
    private String origenName;
    @Column()
    private Long size;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
}
