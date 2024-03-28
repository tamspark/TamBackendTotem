package com.sparklab.TAM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentRule extends BaseEntity {

    private String ruleDescription;
    //    private String apartmentId;
//    @ManyToOne
//    @JoinColumn(name = "hostId", referencedColumnName = "id")
//    private User user;

}
