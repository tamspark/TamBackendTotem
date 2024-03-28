package com.sparklab.TAM.dto.apartment;

import com.sparklab.TAM.dto.UserDTO;
import com.sparklab.TAM.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ApartmentFAQDTO {
    private String question;
    private String answer;
    private UserDTO host;
}
