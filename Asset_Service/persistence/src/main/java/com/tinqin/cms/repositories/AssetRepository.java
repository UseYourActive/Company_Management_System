package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}
