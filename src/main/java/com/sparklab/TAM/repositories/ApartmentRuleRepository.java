package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.ApartmentOption;
import com.sparklab.TAM.model.ApartmentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRuleRepository extends JpaRepository<ApartmentRule,Long> {

    @Query(value="select * from apartment_rule where id in:ids",nativeQuery=true)
    List<ApartmentRule> getAllApartmentOptionsByTheirIds(List<Long> ids);


}
