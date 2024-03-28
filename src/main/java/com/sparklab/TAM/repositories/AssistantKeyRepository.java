package com.sparklab.TAM.repositories;

import com.sparklab.TAM.model.AssistantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantKeyRepository extends JpaRepository<AssistantKey,Long> {
}
