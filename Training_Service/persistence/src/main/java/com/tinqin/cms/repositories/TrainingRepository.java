package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
}
