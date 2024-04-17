package com.avegarlabs.wastemanageraddress.repositories;

import com.avegarlabs.wastemanageraddress.entities.WasteManagerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasteManagerAddressRepository extends JpaRepository<WasteManagerAddressEntity, Long> {

    Optional<WasteManagerAddressEntity> findByWasteManagerId(Long managerId);
}
