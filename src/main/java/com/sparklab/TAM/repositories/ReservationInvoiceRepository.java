package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.ReservationInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationInvoiceRepository  extends JpaRepository<ReservationInvoice,Long> {

}
