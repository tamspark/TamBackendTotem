package com.sparklab.TAM.services;

import com.google.api.client.util.DateTime;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sparklab.TAM.dto.MeetingLinkDTO;
import com.sparklab.TAM.dto.message.SmoobuMessageRequest;
import com.sparklab.TAM.dto.reservation.ReservationDTO;
import com.sparklab.TAM.dto.totem.TotemMeetingLinkDTO;
import com.sparklab.TAM.repositories.TotemCheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@Service
@RequiredArgsConstructor
public class MeetingService {

    private final TotemCheckInRepository totemCheckInRepository;
private final EmailService emailService;

//    public String generateMeetLink(MeetingLinkDTO meetingLinkDTO) throws Exception {
//        String apiKey = "apikey";
//
//        String meetApiUrl = "https://meet.googleapis.com/create";
//
//        String requestUrl = meetApiUrl + "?key=" + apiKey;
//
//        String requestBody = "conferenceSolutionKey.type=hangoutsMeet&requestId=" + URLEncoder.encode(String.valueOf(meetingLinkDTO.getReservationId()), "UTF-8");
//
//        URL url = new URL(requestUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        connection.getOutputStream().write(requestBody.getBytes("UTF-8"));
//
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//        reader.close();
//
//
//        String meetLink = extractMeetLinkFromResponse(response.toString(), String.valueOf(meetingLinkDTO.getReservationId()));
//
//        System.out.println(meetLink);
//        return meetLink;
//    }
//
//    private String extractMeetLinkFromResponse(String response, String reservationId) throws UnsupportedEncodingException {
//
//        JsonParser parser = new JsonParser();
//        JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
//
//
//        if (jsonResponse.has("conferenceData") && jsonResponse.getAsJsonObject("conferenceData").has("entryPoints")) {
//
//            JsonArray entryPoints = jsonResponse.getAsJsonObject("conferenceData").getAsJsonArray("entryPoints");
//
//
//            for (JsonElement entryPoint : entryPoints) {
//                JsonObject entryPointObj = entryPoint.getAsJsonObject();
//                if (entryPointObj.has("entryPointType") && entryPointObj.get("entryPointType").getAsString().equals("video")) {
//
//                    String meetLink = entryPointObj.get("uri").getAsString();
//
//                    meetLink += "?reservationId=" + URLEncoder.encode(reservationId, "UTF-8");
//
//                    return meetLink;
//                }
//            }
//        }
//        return null;
//    }

    public String generateMeetLink(MeetingLinkDTO meetingLinkDTO) {
        return "https://meet.jit.si/" + meetingLinkDTO.getReservationId()
                + "guests=" + meetingLinkDTO.getGuestName();

    }


    public String sendEmailToHost(String apartmentName, String reservationId, String reservationStartDate, String endDate, String guestName, String hostName, String hostEmail, String apartmentId) {

        String meetingLink = generateMeetLink(new MeetingLinkDTO(Long.parseLong(reservationId), guestName));
        emailService.send(hostEmail, buildHostMeetingNotification(apartmentName, reservationId, reservationStartDate, endDate, guestName, hostName, meetingLink).getMessageBody());
        return meetingLink;
        //       return  "redirect:http://192.168.10.168:3000/kyc/"+apartmentId+"/"+reservationId+"/"+guestName+"\"";

    }

    private SmoobuMessageRequest buildHostMeetingNotification(String apartmentName, String reservationId, String reservationStartDate, String endDate, String guestName, String hostName, String meetingLink) {
        SmoobuMessageRequest messageRequest = new SmoobuMessageRequest();

        messageRequest.setSubject("Guest Check-in Notification");
        messageRequest.setMessageBody(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Guest Check-in Notification</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            font-family: Arial, sans-serif;\n" +
                        "            background-color: #f4f4f4;\n" +
                        "            margin: 0;\n" +
                        "            padding: 0;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            max-width: 600px;\n" +
                        "            margin: 20px auto;\n" +
                        "            background-color: #ffffff;\n" +
                        "            padding: 20px;\n" +
                        "            border-radius: 8px;\n" +
                        "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .header {\n" +
                        "            text-align: center;\n" +
                        "            margin-bottom: 20px;\n" +
                        "        }\n" +
                        "        .info {\n" +
                        "            text-align: left;\n" +
                        "            margin-bottom: 20px;\n" +
                        "            padding: 10px;\n" +
                        "        }\n" +
                        "        .btn-container {\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            margin-top: 20px;\n" +
                        "        }\n" +
                        "        .btn {\n" +
                        "            display: inline-block;\n" +
                        "            padding: 12px 24px;\n" +
                        "            margin: 0 10px;\n" +
                        "            border-radius: 5px;\n" +
                        "            text-decoration: none;\n" +
                        "            font-weight: bold;\n" +
                        "            background-color: #007bff;\n" +
                        "            color: white;\n" +
                        "            transition: background-color 0.3s ease;\n" +
                        "        }\n" +
                        "        .btn:hover {\n" +
                        "            background-color: #0056b3;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"header\">\n" +
                        "            <h1>Guest Check-in Notification</h1>\n" +
                        "        </div>\n" +
                        "        <div class=\"info\">\n" +
                        "            <p><strong>Hello " + hostName + ",</strong></p>\n" +
                        "            <p>A guest named <strong>" + guestName + "</strong> has checked in to your apartment <strong>\"" + apartmentName + "\"</strong> for the following period:</p>\n" +
                        "            <p><strong>Reservation ID:</strong> " + reservationId + "</p>\n" +
                        "            <p><strong>Check-in Date:</strong> " + reservationStartDate + "</p>\n" +
                        "            <p><strong>Check-out Date:</strong> " + endDate + "</p>\n" +
                        "        </div>\n" +
                        "        <div class=\"btn-container\">\n" +
                        "            <a class=\"btn\" href=\"" + meetingLink + "\">Enter Meeting</a>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>"
        );

        return messageRequest;
    }

    public Object generateTotemMeetLink(TotemMeetingLinkDTO totemMeetingLinkDTO) {
        //TODO check if checkin status successfully or not but should be separated like when should become succesfully before or after the call;
        return "https://meet.jit.si/" + totemMeetingLinkDTO.getCheckInId()
                + "/guests=" + totemCheckInRepository.findById(totemMeetingLinkDTO.getCheckInId()).get().getName();
    }
}
