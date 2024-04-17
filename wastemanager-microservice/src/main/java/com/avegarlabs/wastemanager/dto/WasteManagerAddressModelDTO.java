package com.avegarlabs.wastemanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WasteManagerAddressModelDTO {

    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "The Id of Waste Manager is mandatory")
    private Long wasteManagerId;
}
