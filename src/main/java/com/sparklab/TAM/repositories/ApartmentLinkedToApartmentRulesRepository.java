package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.ApartmentLinkedToApartmentRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentLinkedToApartmentRulesRepository  extends JpaRepository<ApartmentLinkedToApartmentRules,Long> {

    @Query(value = "select apartment_rule_id from apartment_linked_to_apartment_rules where apartment_id=:apartmentId",nativeQuery = true)
    List<Long> getOptionIdsByApartmentId(Long apartmentId);

    void deleteByApartmentIdAndApartmentRule_Id(String apartmentId, Long apartmentRuleId);
}


