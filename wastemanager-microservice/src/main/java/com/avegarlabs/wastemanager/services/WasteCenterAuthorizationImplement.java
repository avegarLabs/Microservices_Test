package com.avegarlabs.wastemanager.services;


import com.avegarlabs.wastemanager.dto.WasteCenterAuthorizationListItems;
import com.avegarlabs.wastemanager.entities.WasteCenterAuthorizationEntity;
import com.avegarlabs.wastemanager.entities.WasteManagerEntity;
import com.avegarlabs.wastemanager.repositories.WasteCenterAuthorizationRepository;
import com.avegarlabs.wastemanager.services.abstracts.WasteCenterAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WasteCenterAuthorizationImplement implements WasteCenterAuthorizationService {

    @Autowired
    WasteCenterAuthorizationRepository wasteCenterAuthorizationEntityRepository;

    @Override
    public void createWasteCenterAuthorizationByWasteManager(List<String> authorizationNumbers, WasteManagerEntity manager) {
        List<WasteCenterAuthorizationEntity> authorizationEntityList = new ArrayList<>();
        for (String authorizationNumber : authorizationNumbers) {
            authorizationEntityList.add(buildEntity(authorizationNumber, manager));
        }
        wasteCenterAuthorizationEntityRepository.saveAll(authorizationEntityList);
    }

    @Override
    @Transactional
    public void updateWasteCenterAuthorizationByWasteManager(List<String> authorizationNumbers, WasteManagerEntity manager) {
        List<String> authNumbresInBD = wasteCenterAuthorizationEntityRepository.findAllByWasteManagerId(manager.getId()).stream().map(WasteCenterAuthorizationEntity::getAuthorizationNumber).toList();
        List<String> numbersToRemove = authNumbresInBD.stream().filter(item -> !authorizationNumbers.contains(item)).toList();
        List<String> numbersToAdd = authorizationNumbers.stream().filter(item -> !authNumbresInBD.contains(item)).toList();
        if(numbersToRemove.size()>0){
          wasteCenterAuthorizationEntityRepository.deleteByAuthorizationNumbers(numbersToRemove);
        }
        if(numbersToAdd.size()>0){
            createWasteCenterAuthorizationByWasteManager(numbersToAdd, manager);
        }
    }

    @Override
    public List<WasteCenterAuthorizationListItems> getWasteCenterAuthorizationListItemsByManagerId(Long managerId) {
        return wasteCenterAuthorizationEntityRepository.findAllByWasteManagerId(managerId).stream().map(this::mapWasteCenterAuthorizationListItemsFromWasteCenterAuthorizationEntity).toList();
    }

    private WasteCenterAuthorizationEntity buildEntity(String number, WasteManagerEntity manager){
        return WasteCenterAuthorizationEntity.builder()
                .authorizationNumber(number)
                .wasteManager(manager)
                .build();
    }

    public WasteCenterAuthorizationListItems mapWasteCenterAuthorizationListItemsFromWasteCenterAuthorizationEntity(WasteCenterAuthorizationEntity entity){
        return WasteCenterAuthorizationListItems.builder()
                .id(entity.getId())
                .authorizationNumber(entity.getAuthorizationNumber())
                .build();
    }

}
