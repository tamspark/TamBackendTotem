package com.sparklab.TAM.model;

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
public class ApartmentLinkedToApartmentRules extends BaseEntity{

    private String apartmentId;
    @ManyToOne
    @JoinColumn(name = "apartmentRuleId",referencedColumnName = "id")
    private ApartmentRule apartmentRule;
}
