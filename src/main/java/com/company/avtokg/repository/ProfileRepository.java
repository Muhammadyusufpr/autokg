package com.company.avtokg.repository;

import com.company.avtokg.entity.ProfileEntity;
import com.company.avtokg.enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByEmailAndPassword(String email, String password);

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findById(String id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set attach.id = :attachId where id = :id")
    int updateAttach(@Param("attachId") String attachId, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status = :status where id = :id")
    int updateStatus(@Param("status") ProfileStatus status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status = :status where id = :id")
    int changeStatus(@Param("status") ProfileStatus status, @Param("id") String id);


    Page<ProfileEntity> findById(Pageable pageable, String id);

}
