package com.company.avtokg.entity;

import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;


}
