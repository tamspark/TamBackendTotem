package com.sparklab.TAM.dto;

import com.sparklab.TAM.model.CheckIn;
import com.sparklab.TAM.model.TotemCheckIn;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class CheckInDocumentsDTO {

    private List<MultipartFile> documents;
    private TotemCheckIn checkIn;



}
