package com.avegarlabs.wastemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WasteManagerModelDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "NIF is mandatory")
    @Size(min = 9, max = 9, message = "The nif must contain 9 characters")
    private String nif;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotEmpty(message = "Provide at least one authorization number")
    private List<String> wasteCenterAuthorizationNumbers;
}
