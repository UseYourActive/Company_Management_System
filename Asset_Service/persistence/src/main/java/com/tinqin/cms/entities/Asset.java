package com.tinqin.cms.entities;

import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "assets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_type", nullable = false)
    private AssetType assetType;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_status", nullable = false)
    private AssetStatus assetStatus;
}
