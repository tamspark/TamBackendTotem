package com.sparklab.TAM.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInvoice extends BaseEntity{

    private Long reservationId;
    @Lob
    @Column(length = 2147483647)
    private byte[] invoice;
    private String invoiceName;
    private String invoiceType;
    private LocalDateTime createdDate;
}
