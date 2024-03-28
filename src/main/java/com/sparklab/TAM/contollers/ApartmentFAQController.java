package com.sparklab.TAM.contollers;

import com.sparklab.TAM.model.ApartmentFAQ;
import com.sparklab.TAM.services.ApartmentFAQService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TAM/apartmentFAQ")
@RequiredArgsConstructor
public class ApartmentFAQController {

    private final ApartmentFAQService apartmentFAQService;


    @PostMapping("saveFAQ")
    public ResponseEntity<?> saveOrUpdateFAQs(@RequestBody ApartmentFAQ apartmentFAQ){

       return new ResponseEntity<>(apartmentFAQService.saveOrUpdateFAQs(apartmentFAQ), HttpStatus.OK);

    }

    @GetMapping("getByUser/{userId}")
    public ResponseEntity<?> getFAQsByUserId(@PathVariable String userId){

        return new ResponseEntity<>(apartmentFAQService.getFAQsByUserId(userId), HttpStatus.OK);

    }
    //TODO now is all of them but get them for host that has this apartment
    @GetMapping("getByApartment/{apartmentId}")
    public ResponseEntity<?> getFAQs(@PathVariable String apartmentId){
        return new ResponseEntity<>(apartmentFAQService.getFAQs(), HttpStatus.OK);
    }

}
