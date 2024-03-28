package com.sparklab.TAM.services;

import com.sparklab.TAM.model.ApartmentLinkedToApartmentRules;
import com.sparklab.TAM.model.ApartmentRule;
import com.sparklab.TAM.repositories.ApartmentLinkedToApartmentRulesRepository;
import com.sparklab.TAM.repositories.ApartmentRuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentRuleService {


    private final ApartmentRuleRepository apartmentRuleRepository;
    private final ApartmentLinkedToApartmentRulesRepository apartmentLinkedToApartmentRulesRepository;


    public String saveOrUpdateApartmentRule(ApartmentRule apartmentRule) {
        if(apartmentRule!=null) {
            apartmentRuleRepository.save(apartmentRule);
            return "Apartment Rule was saved successfully.";
        }
        return null;
    }


    public String linkApartmentWithRule(String apartmentId, String apartmentRuleId) {
        Long parsedApartmentRuleId = Long.parseLong(apartmentRuleId);
        ApartmentLinkedToApartmentRules apartmentLinkedToApartmentRules = new ApartmentLinkedToApartmentRules();
        apartmentLinkedToApartmentRules.setApartmentId(apartmentId);
        apartmentLinkedToApartmentRules.setApartmentRule(apartmentRuleRepository.findById(parsedApartmentRuleId).get());
        apartmentLinkedToApartmentRulesRepository.save(apartmentLinkedToApartmentRules);
        return "Apartment Rule linked successfully with Apartment";
    }


    public List<ApartmentRule> getApartmentRulesPerApartment(String apartmentId) {

        Long parseApartmentId = Long.parseLong(apartmentId);
        List<Long> apartmentRuleIds = apartmentLinkedToApartmentRulesRepository.getOptionIdsByApartmentId(parseApartmentId);
        return apartmentRuleRepository.getAllApartmentOptionsByTheirIds(apartmentRuleIds);
    }


    @Transactional
    public String removeFAQSForApartment(String apartmentId, String apartmentRuleId) {
        apartmentLinkedToApartmentRulesRepository.deleteByApartmentIdAndApartmentRule_Id(apartmentId, Long.valueOf(apartmentRuleId));
        return "ApartmentRule was successfully removed from apartment";
    }
}
