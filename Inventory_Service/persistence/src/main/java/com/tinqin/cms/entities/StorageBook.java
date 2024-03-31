package com.tinqin.cms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "storage_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageBook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
