package com.sparklab.TAM.services;

import com.sparklab.TAM.contollers.ApartmentFAQController;
import com.sparklab.TAM.dto.UserDTO;
import com.sparklab.TAM.dto.apartment.ApartmentFAQDTO;
import com.sparklab.TAM.model.ApartmentFAQ;
import com.sparklab.TAM.model.User;
import com.sparklab.TAM.repositories.ApartmentFAQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentFAQService {

    private final ApartmentFAQRepository apartmentFAQRepository;

    public String saveOrUpdateFAQs(ApartmentFAQ apartmentFAQ) {
        if (apartmentFAQ != null) {
            apartmentFAQRepository.save(apartmentFAQ);
            return "Apartment FAQ was saved successfully .";
        }
        return null;
    }


    public List<ApartmentFAQDTO> getFAQsByUserId(String userId) {
        Long parsedHostId = Long.parseLong(userId);
        List<ApartmentFAQ> apartmentFAQs = apartmentFAQRepository.findByUser_Id(parsedHostId);
        return getApartmentFAQDTOS(apartmentFAQs);
    }

    public List<ApartmentFAQDTO> getFAQs() {
        List<ApartmentFAQ> apartmentFAQs = apartmentFAQRepository.findAll();
        return getApartmentFAQDTOS(apartmentFAQs);
    }

    //TODO should you implement for each apartment its own faqs??
    private List<ApartmentFAQDTO> getApartmentFAQDTOS(List<ApartmentFAQ> apartmentFAQs) {
        List<ApartmentFAQDTO> apartmentFAQDTOList = new ArrayList<>();
        for (ApartmentFAQ af : apartmentFAQs) {
            ApartmentFAQDTO apartmentFAQDTO = new ApartmentFAQDTO();
            apartmentFAQDTO.setQuestion(af.getQuestion());
            apartmentFAQDTO.setAnswer(af.getAnswer());
           if(af.getUser()!=null){
            UserDTO host = new UserDTO();
            host.setId(af.getUser().getId());
            host.setEmail(af.getUser().getEmail());
            apartmentFAQDTO.setHost(host);}
            apartmentFAQDTOList.add(apartmentFAQDTO);
        }
        return apartmentFAQDTOList;
    }
}
