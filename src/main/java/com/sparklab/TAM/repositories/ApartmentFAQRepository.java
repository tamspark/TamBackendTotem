package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.ApartmentFAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentFAQRepository extends JpaRepository<ApartmentFAQ,Long> {


    List<ApartmentFAQ> findByUser_Id (Long parsedHosId);
}
