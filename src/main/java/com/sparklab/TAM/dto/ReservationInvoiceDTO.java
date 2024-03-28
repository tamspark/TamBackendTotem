package com.sparklab.TAM.dto;

import lombok.Data;

@Data
public class ReservationInvoiceDTO {
    private Long id;
    private byte[] pdf;
    private String name;
    private String type;
    private Long reservationId;
}
