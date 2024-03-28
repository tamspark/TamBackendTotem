package com.sparklab.TAM.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDocuments extends BaseEntity {

    @Lob
    @Column(length = 2147483647)
    private byte[] document;
    private String docType;
    private String docName;
    @ManyToOne
    @JoinColumn(name = "totemCheckInId", referencedColumnName = "id")
    private TotemCheckIn checkIn;

}
