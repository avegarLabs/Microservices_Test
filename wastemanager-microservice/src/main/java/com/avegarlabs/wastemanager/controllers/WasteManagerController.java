package com.avegarlabs.wastemanager.controllers;

import com.avegarlabs.wastemanager.dto.WasteManagerModelDTO;
import com.avegarlabs.wastemanager.services.WasteManagerImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wastemanager")
public class WasteManagerController {

    @Autowired
    WasteManagerImplement serviceImplement;

    @PostMapping
    public ResponseEntity<?> persistWasteManager(@Valid @RequestBody WasteManagerModelDTO modelDTO, BindingResult bindingResult) throws Exception {
        return serviceImplement.create(modelDTO, bindingResult);
    }


    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findWasteManagerById(@PathVariable Long id) throws Exception {
        return serviceImplement.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWasteManager(@Valid @RequestBody WasteManagerModelDTO modelDTO, BindingResult bindingResult, @PathVariable Long id) throws Exception {
        return serviceImplement.update(id, modelDTO, bindingResult);
    }
}
