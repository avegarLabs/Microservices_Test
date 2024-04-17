package com.avegarlabs.wastemanageraddress.controllers;

import com.avegarlabs.wastemanageraddress.dto.WasteManagerAddressModelDTO;
import com.avegarlabs.wastemanageraddress.services.WasteManagerAddressImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addressmanager")
public class WasteManagerAddressController {

    @Autowired
    WasteManagerAddressImplementation serviceImplement;

    @PostMapping
    public ResponseEntity<?> persistAddressInManager(@Valid @RequestBody WasteManagerAddressModelDTO modelDTO) throws Exception {
        return serviceImplement.create(modelDTO);
    }


    @GetMapping("/addressByManagerId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        return serviceImplement.finAddressByManagerId(id);
    }

    @PutMapping("/updateByManagerId/{id}")
    public ResponseEntity<?> updateWasteManager(@Valid @RequestBody WasteManagerAddressModelDTO modelDTO, @PathVariable Long id) throws Exception {
        return serviceImplement.update(id, modelDTO);
    }
}
