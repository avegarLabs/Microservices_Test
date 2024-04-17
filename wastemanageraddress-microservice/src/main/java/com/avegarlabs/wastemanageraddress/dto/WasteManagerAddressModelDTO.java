package com.avegarlabs.wastemanageraddress.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WasteManagerAddressModelDTO {

    private String address;
    private Long wasteManagerId;
}
