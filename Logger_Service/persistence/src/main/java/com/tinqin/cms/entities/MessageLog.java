package com.tinqin.cms.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MessageLog(UUID id,
                         String email,
                         String message,
                         OffsetDateTime date) {
    public MessageLog(Chronology chronology) {
        this(chronology.getId(), chronology.getEmail(), chronology.getLog(), chronology.getCreatedOn());
    }
}
