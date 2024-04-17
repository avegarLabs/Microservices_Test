package com.avegarlabs.wastemanager.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WasteCenterAuthorizationListItems {
    private Long id;
    private String authorizationNumber;
}
