package com.avegarlabs.wastemanager.repositories;

import com.avegarlabs.wastemanager.entities.WasteCenterAuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteCenterAuthorizationRepository extends JpaRepository<WasteCenterAuthorizationEntity, Long> {

    @Modifying
    @Query("DELETE FROM WasteCenterAuthorizationEntity wca WHERE wca.authorizationNumber IN :authNumbers")
    void deleteByAuthorizationNumbers(@Param("authNumbers") List<String> authNumbers);

    List<WasteCenterAuthorizationEntity> findAllByWasteManagerId(Long id);
}
