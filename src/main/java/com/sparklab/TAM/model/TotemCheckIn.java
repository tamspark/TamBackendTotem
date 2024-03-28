package com.sparklab.TAM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotemCheckIn extends BaseEntity{
    private String name;
    private String surname;
    private String documentId;
    private LocalDate birthDate;
    private LocalDate documentExpiry;
    private LocalDateTime checkInTime;
    private String apartmentId;
    @Enumerated(EnumType.STRING)
    private CheckInStatus checkInStatus;
}
