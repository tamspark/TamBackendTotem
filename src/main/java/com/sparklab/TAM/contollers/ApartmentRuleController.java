package com.sparklab.TAM.contollers;

import com.sparklab.TAM.model.ApartmentRule;
import com.sparklab.TAM.services.ApartmentRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TAM/apartmentRule")
@RequiredArgsConstructor
public class ApartmentRuleController {

    private final ApartmentRuleService apartmentRuleService;


    @PostMapping("saveApartmentRule")
    public ResponseEntity<?> saveOrUpdateApartmentRule(@RequestBody ApartmentRule apartmentRule){

        return new ResponseEntity<>(apartmentRuleService.saveOrUpdateApartmentRule(apartmentRule), HttpStatus.OK);

    }

    @PutMapping("/linkApartmentWithRule/{apartmentId}/{apartmentRuleId}")
    public ResponseEntity<?> linkApartmentWithRule(@PathVariable String apartmentId, @PathVariable String apartmentRuleId){

        return new ResponseEntity<>(apartmentRuleService.linkApartmentWithRule(apartmentId,apartmentRuleId), HttpStatus.OK);

    }

    @GetMapping("getByApartment/{apartmentId}")
    public ResponseEntity<?> getRulesByApartmentId(@PathVariable String apartmentId){

        return new ResponseEntity<>(apartmentRuleService.getApartmentRulesPerApartment(apartmentId), HttpStatus.OK);

    }


    @DeleteMapping ("removeRuleForApartment/{apartmentId}/{apartmentRuleId}")
    public ResponseEntity<?> removeRulesForApartment(@PathVariable String apartmentId, @PathVariable String apartmentRuleId){
        return new ResponseEntity<>(apartmentRuleService.removeFAQSForApartment(apartmentId,apartmentRuleId), HttpStatus.OK);
    }

}
