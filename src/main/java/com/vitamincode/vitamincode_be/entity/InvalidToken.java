package com.vitamincode.vitamincode_be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(schema = "vitamincode_class", name = "invalidated_token")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvalidToken {
    @Id
    @Column(name = "id")
    private String invalidTokenId;

    @Column(name = "exp_time")
    private Timestamp expTime;
}
