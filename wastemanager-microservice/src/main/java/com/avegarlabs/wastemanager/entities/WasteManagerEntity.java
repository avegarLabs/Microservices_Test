package com.avegarlabs.wastemanager.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class WasteManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nif;


    @OneToMany(mappedBy = "wasteManager", cascade = CascadeType.ALL)
    private List<WasteCenterAuthorizationEntity> listOfWasteCenterAuthorization = new ArrayList<>();

    @Column(nullable = false)
    private Boolean isEnabled;

    @Version
    private Long version = 0L;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;


}
