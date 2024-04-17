package com.avegarlabs.wastemanager.services.abstracts;

import com.avegarlabs.wastemanager.dto.WasteCenterAuthorizationListItems;
import com.avegarlabs.wastemanager.entities.WasteManagerEntity;

import java.util.List;

public interface WasteCenterAuthorizationService {

    void createWasteCenterAuthorizationByWasteManager(List<String> authorizationNumbers, WasteManagerEntity manager);

    void updateWasteCenterAuthorizationByWasteManager(List<String> authorizationNumbers, WasteManagerEntity manager);

    List<WasteCenterAuthorizationListItems> getWasteCenterAuthorizationListItemsByManagerId(Long managerId);

}
