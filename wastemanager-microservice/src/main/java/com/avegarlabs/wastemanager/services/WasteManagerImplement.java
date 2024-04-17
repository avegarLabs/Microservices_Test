package com.avegarlabs.wastemanager.services;

import com.avegarlabs.wastemanager.client.ManagerAddressClient;
import com.avegarlabs.wastemanager.dto.*;
import com.avegarlabs.wastemanager.entities.WasteManagerEntity;
import com.avegarlabs.wastemanager.repositories.WasteManagerRepository;
import com.avegarlabs.wastemanager.services.abstracts.WasteManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;

@Service
public class WasteManagerImplement implements WasteManagerService {

    @Autowired
    WasteManagerRepository wasteManagerEntityRepository;

    @Autowired
    WasteCenterAuthorizationImplement wasteCenterAuthorizationServiceImplement;

    @Autowired
    ManagerAddressClient addressClient;

    @Override
    public ResponseEntity<?> create(WasteManagerModelDTO model, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append(". ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        WasteManagerEntity entity = wasteManagerEntityRepository.save(mapWasteManagerEntityFromModelDTO(model));
        wasteCenterAuthorizationServiceImplement.createWasteCenterAuthorizationByWasteManager(model.getWasteCenterAuthorizationNumbers(), entity);
        WasteManagerAddressModelDTO addressModelDTO = builAddressModelDTO(model.getAddress(), entity.getId());
        addressClient.persistAddressInManager(addressModelDTO);
        WasteManagerItemDTO managerItemDTO = mapWasteManagerItemDTOFromEntity(entity);
        return ResponseEntity.ok(managerItemDTO);
    }

    @Override
    public ResponseEntity<?> update(Long id, WasteManagerModelDTO dto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append(". ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        Optional<WasteManagerEntity> wasteManager = wasteManagerEntityRepository.findById(id);
        if (wasteManager.isEmpty()) {
            return ResponseEntity.badRequest().body("Waste Manager not found");
        }

        WasteManagerEntity wasteManagerUpdate = wasteManagerEntityRepository.save(updateFieldFromModelDTO(wasteManager.get(), dto));
        wasteCenterAuthorizationServiceImplement.updateWasteCenterAuthorizationByWasteManager(dto.getWasteCenterAuthorizationNumbers(), wasteManagerUpdate);
        WasteManagerAddressModelDTO addressModelDTO = builAddressModelDTO(dto.getAddress(), wasteManager.get().getId());
        addressClient.updateWasteManager(addressModelDTO, wasteManager.get().getId());
        WasteManagerItemDTO itemDTO = mapWasteManagerItemDTOFromEntity(wasteManagerUpdate);
        return ResponseEntity.ok(itemDTO);
    }

    @Override
    public ResponseEntity<?> findById(Long wasteManagerId) throws Exception {

        Optional<WasteManagerEntity> wasteManager = wasteManagerEntityRepository.findById(wasteManagerId);
        if (wasteManager.isEmpty()) {
            return ResponseEntity.badRequest().body("Waste Manager not found");
        }

        WasteManagerItemDTO managerItemDTO = mapWasteManagerItemDTOFromEntity(wasteManager.get());
        return ResponseEntity.ok(managerItemDTO);
    }

    private WasteManagerEntity mapWasteManagerEntityFromModelDTO(WasteManagerModelDTO modelDTO) {
        return WasteManagerEntity.builder()
                .name(modelDTO.getName())
                .nif(modelDTO.getNif())
                .isEnabled(true)
                .build();
    }

    private WasteManagerItemDTO mapWasteManagerItemDTOFromEntity(WasteManagerEntity entity) throws Exception {
        List<WasteCenterAuthorizationListItems> authorizationListItems = wasteCenterAuthorizationServiceImplement.getWasteCenterAuthorizationListItemsByManagerId(entity.getId());
        WasteManagerAddressResponseDTO wasteManagerAddressResponseDTO = addressClient.findById(entity.getId());
        return WasteManagerItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nif(entity.getNif())
                .wasteManagerAddressResponseDTO(wasteManagerAddressResponseDTO)
                .authorizationListItems(authorizationListItems)
                .build();
    }

    private WasteManagerEntity updateFieldFromModelDTO(WasteManagerEntity entity, WasteManagerModelDTO modelDTO) {
        entity.setName(modelDTO.getName());
        entity.setNif(modelDTO.getNif());
        return entity;
    }

    private WasteManagerAddressModelDTO builAddressModelDTO(String address, Long managerId) throws Exception {
        return WasteManagerAddressModelDTO.builder()
                .address(address)
                .wasteManagerId(managerId)
                .build();
    }




}
