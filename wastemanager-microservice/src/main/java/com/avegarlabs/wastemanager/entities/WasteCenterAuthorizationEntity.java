package com.avegarlabs.wastemanager.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class WasteCenterAuthorizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorizationNumber;

    @ManyToOne
    @JoinColumn(name = "waste_manager_id", referencedColumnName = "id")
    private WasteManagerEntity wasteManager;

}
