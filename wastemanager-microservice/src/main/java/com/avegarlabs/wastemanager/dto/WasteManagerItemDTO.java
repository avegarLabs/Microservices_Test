package com.avegarlabs.wastemanager.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WasteManagerItemDTO {
    private Long id;
    private String name;
    private String nif;
    private WasteManagerAddressResponseDTO wasteManagerAddressResponseDTO;
    private List<WasteCenterAuthorizationListItems> authorizationListItems;
}
