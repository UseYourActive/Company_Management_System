package com.tinqin.cms.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MessageLog(UUID id,
                         String message,
                         OffsetDateTime date) {
    public MessageLog(Chronology chronology) {
        this(chronology.getId(), chronology.getLog(), chronology.getCreatedOn());
    }
}
