package com.tinqin.cms.entities;

import com.tinqin.cms.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tokens")
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "expired")
    private boolean expired;

    @Column(name = "revoked")
    private boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
