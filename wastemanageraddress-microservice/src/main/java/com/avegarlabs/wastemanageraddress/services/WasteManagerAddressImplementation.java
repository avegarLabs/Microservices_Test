package com.avegarlabs.wastemanageraddress.services;

import com.avegarlabs.wastemanageraddress.dto.WasteManagerAddressModelDTO;
import com.avegarlabs.wastemanageraddress.dto.WasteManagerAddressResponseDTO;
import com.avegarlabs.wastemanageraddress.entities.WasteManagerAddressEntity;
import com.avegarlabs.wastemanageraddress.repositories.WasteManagerAddressRepository;
import com.avegarlabs.wastemanageraddress.services.abstracts.WasteManagerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class WasteManagerAddressImplementation implements WasteManagerAddressService {

    @Autowired
    WasteManagerAddressRepository managerAddressRepository;
    @Override
    public ResponseEntity create(WasteManagerAddressModelDTO model) throws Exception {
        WasteManagerAddressEntity addressEntity = mapWasteManagerAddressEntityFromModelDTO(model);
        WasteManagerAddressResponseDTO responseDTO = mapManagerAddressResponseDTOFromEntity(managerAddressRepository.save(addressEntity));
        return ResponseEntity.ok(responseDTO);

    }

    @Override
    public ResponseEntity update(Long id, WasteManagerAddressModelDTO dto) throws Exception {
       Optional<WasteManagerAddressEntity> addressEntity = managerAddressRepository.findByWasteManagerId(id);
        if(addressEntity.isEmpty()){
            return ResponseEntity.badRequest().body("Address not found");
        }
        addressEntity.get().setAddress(dto.getAddress());
        WasteManagerAddressResponseDTO responseDTO = mapManagerAddressResponseDTOFromEntity(managerAddressRepository.save(addressEntity.get()));
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity finAddressByManagerId(Long wasteManagerId) throws Exception {
        Optional<WasteManagerAddressEntity> addressEntity = managerAddressRepository.findByWasteManagerId(wasteManagerId);
        if(addressEntity.isEmpty()){
            return ResponseEntity.badRequest().body("Address not found");
        }
        WasteManagerAddressResponseDTO responseDTO = mapManagerAddressResponseDTOFromEntity(managerAddressRepository.save(addressEntity.get()));
        return ResponseEntity.ok(responseDTO);
    }


    private WasteManagerAddressEntity mapWasteManagerAddressEntityFromModelDTO(WasteManagerAddressModelDTO modelDTO){
        return WasteManagerAddressEntity.builder()
                .address(modelDTO.getAddress())
                .wasteManagerId(modelDTO.getWasteManagerId())
                .isEnabled(true)
                .build();
    }

    private WasteManagerAddressResponseDTO mapManagerAddressResponseDTOFromEntity(WasteManagerAddressEntity entity){
        return WasteManagerAddressResponseDTO.builder()
                .address(entity.getAddress())
                .build();
    }

}
