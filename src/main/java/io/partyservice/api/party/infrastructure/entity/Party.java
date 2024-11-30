package io.partyservice.api.party.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation_type")
    private int relationType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "terminated_at")
    private LocalDateTime terminatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}