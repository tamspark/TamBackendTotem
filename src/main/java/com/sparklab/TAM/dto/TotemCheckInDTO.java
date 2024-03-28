package com.sparklab.TAM.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class TotemCheckInDTO {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String identifierDocumentId;
    private LocalDate identifierDocumentExpiry;
    private LocalDateTime checkInTime;
    private String apartmentId;
    private List<MultipartFile> documents;


}
