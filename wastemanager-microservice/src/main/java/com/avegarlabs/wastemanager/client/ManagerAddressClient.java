package com.avegarlabs.wastemanager.client;

import com.avegarlabs.wastemanager.dto.WasteManagerAddressModelDTO;
import com.avegarlabs.wastemanager.dto.WasteManagerAddressResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "wastemanageraddress-microservice")
public interface ManagerAddressClient {


    @PostMapping("/api/addressmanager")
    WasteManagerAddressResponseDTO persistAddressInManager(@Valid @RequestBody WasteManagerAddressModelDTO modelDTO) throws Exception;


    @GetMapping("/api/addressmanager/addressByManagerId/{id}")
    WasteManagerAddressResponseDTO findById(@PathVariable Long id) throws Exception;


    @PutMapping("/api/addressmanager/updateByManagerId/{id}")
    WasteManagerAddressResponseDTO updateWasteManager(@Valid @RequestBody WasteManagerAddressModelDTO modelDTO, @PathVariable Long id) throws Exception;

}
