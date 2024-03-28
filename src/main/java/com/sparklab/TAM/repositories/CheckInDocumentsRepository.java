package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.CheckInDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInDocumentsRepository extends JpaRepository<CheckInDocuments,Long> {


    List<CheckInDocuments> findByCheckInId(Long checkInId);

}
