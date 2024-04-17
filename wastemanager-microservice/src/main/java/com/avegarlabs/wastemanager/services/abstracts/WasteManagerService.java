package com.avegarlabs.wastemanager.services.abstracts;

import com.avegarlabs.wastemanager.dto.WasteManagerModelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface WasteManagerService {

    ResponseEntity create (WasteManagerModelDTO model, BindingResult bindingResult ) throws Exception;
    ResponseEntity update(Long id, WasteManagerModelDTO dto, BindingResult bindingResult ) throws Exception;
    ResponseEntity findById(Long wasteManagerId) throws Exception;
}
