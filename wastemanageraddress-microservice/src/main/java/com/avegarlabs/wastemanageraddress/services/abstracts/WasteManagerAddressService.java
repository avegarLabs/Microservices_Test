package com.avegarlabs.wastemanageraddress.services.abstracts;

import com.avegarlabs.wastemanageraddress.dto.WasteManagerAddressModelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface WasteManagerAddressService {
    ResponseEntity create(WasteManagerAddressModelDTO model ) throws Exception;
    ResponseEntity update(Long id, WasteManagerAddressModelDTO dto ) throws Exception;
    ResponseEntity finAddressByManagerId(Long wasteManagerId) throws Exception;
}
