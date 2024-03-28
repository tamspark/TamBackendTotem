package com.sparklab.TAM.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApartmentFAQ extends BaseEntity {

    private String question;
    @Column(length=5000)
    private String answer;
    //    private String apartmentId;
    @ManyToOne
    @JoinColumn(name = "hostId", referencedColumnName = "id")
    private User user;
}
