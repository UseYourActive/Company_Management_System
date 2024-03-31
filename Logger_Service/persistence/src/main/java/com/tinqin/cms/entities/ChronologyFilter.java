package com.tinqin.cms.entities;

import com.tinqin.cms.enums.Type;

import java.time.OffsetDateTime;

public record ChronologyFilter(String email,
                               Type type,
                               OffsetDateTime createdAfter,
                               OffsetDateTime createdBefore,
                               int page,
                               int pageSize) {
}
