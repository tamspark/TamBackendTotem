package com.sparklab.TAM.contollers;

import com.sparklab.TAM.dto.CheckInDocumentsDTO;
import com.sparklab.TAM.dto.TotemCheckInDTO;
import com.sparklab.TAM.model.CheckInDocuments;
import com.sparklab.TAM.model.TotemCheckIn;
import com.sparklab.TAM.services.CheckInService;
import com.sparklab.TAM.services.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@AllArgsConstructor
@RequestMapping("/TAM/checkin")
public class CheckInController {

    private final CheckInService checkInService;
    private final MeetingService meetingService;


    @PostMapping("{userId}/{apartmentId}")
    public ResponseEntity<?> doCheckIn(@PathVariable String userId, @PathVariable int apartmentId) {
        return checkInService.doCheckIn(userId, apartmentId);
        //TODO link with reservationId
    }

    @PostMapping("saveTotemCheckin")
    public ResponseEntity<?> checkIn(@ModelAttribute TotemCheckInDTO totemCheckInDTO) {
        return new ResponseEntity<>(checkInService.totemCheckIn(totemCheckInDTO), HttpStatus.OK);

    }





    @GetMapping("/meeting/{apartmentName}/{reservationId}/{reservationStartDate}/{endDate}/{guestName}/{hostName}/{hostemail}/{apartmentId}")
    public RedirectView manageMeetingLink(@PathVariable String apartmentName, @PathVariable String reservationId, @PathVariable String reservationStartDate, @PathVariable String endDate, @PathVariable String guestName, @PathVariable String hostName, @PathVariable String hostemail, @PathVariable String apartmentId) {
        RedirectView redirectView = new RedirectView();
        String link = meetingService.sendEmailToHost(apartmentName, reservationId, reservationStartDate, endDate, guestName, hostName, hostemail, apartmentId);
        redirectView.setUrl(link);
        return redirectView;

    }


    @PostMapping("/uploadDocuments")
    public ResponseEntity<?> uploadDocumentsBeforeCheckIn(@ModelAttribute CheckInDocumentsDTO checkInDocumentDTO) {
        return new ResponseEntity<>(checkInService.uploadDocumentsBeforeCheckIn(checkInDocumentDTO), HttpStatus.OK);
    }

    @GetMapping("/host/getDocuments/allDocumentsPerCheckIn/{checkInId}")
    public ResponseEntity<?> getDocumentsOfCheckIn(@PathVariable String checkInId) {
        //TODO can be done with reservationId to get all the documents linked with an reservation
        return new ResponseEntity<>(checkInService.getDocumentsOfCheckIn(checkInId), HttpStatus.OK);
    }


    @GetMapping("downloadDocument/{documentId}")
    public ResponseEntity<?>downloadDocument(@PathVariable String documentId){
        return checkInService.downloadDocument(documentId);
    }
}
