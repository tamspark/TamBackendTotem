package com.sparklab.TAM.services;

import com.sparklab.TAM.dto.reservation.ReservationDTO;
import com.sparklab.TAM.model.ApartmentOption;
import com.sparklab.TAM.model.User;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceGeneratorService {


    public static byte[] generateInvoice(ReservationDTO reservationDTO, User user, List<ApartmentOption> apartmentOptionList, String paymentTerms) {
        StringBuilder html = new StringBuilder();

        // Generate HTML content for the invoice
        html.append("<html><head><style>")
                .append("body {font-family: Arial, sans-serif;}")
                .append("h1, h2 {text-align: center;}")
                .append("table {width: 100%; border-collapse: collapse;}")
                .append("table, th, td {border: 1px solid black; padding: 8px;}")
                .append("th {background-color: #f2f2f2;}")
                .append("</style><title>Invoice</title></head><body>");
        html.append("<h1>Invoice</h1>");

        // Invoice Details
        html.append("<h2>Invoice Details</h2>");
        html.append("<p><b>Invoice Number:</b> ").append(generateInvoiceNumber()).append("</p>");
        html.append("<p><b>Invoice Date:</b> ").append(formatDate(new Date())).append("</p>");

        // Host Information
        html.append("<h2>Host Information</h2>");
        html.append("<p><b>Name:</b> ").append(user.getFirstName()).append("</p>");
        html.append("<p><b>Email:</b> ").append(user.getEmail()).append("</p>");
        //TODO phone number because i put username for the moment
        html.append("<p><b>Phone Number:</b> ").append(user.getEmail()).append("</p>");

        // Guest Information
        html.append("<h2>Guest Information</h2>");
        html.append("<p><b>First Name:</b> ").append(reservationDTO.getFirstname()).append("</p>");
        html.append("<p><b>Last Name:</b> ").append(reservationDTO.getLastname()).append("</p>");
        html.append("<p><b>Email:</b> ").append(reservationDTO.getEmail()).append("</p>");
        html.append("<p><b>Phone Number:</b> ").append(reservationDTO.getPhone()).append("</p>");

        // Reservation Details
        html.append("<h2>Reservation Details</h2>");
        html.append("<table>");
        html.append("<tr><th>Apartment Name</th><td>").append(reservationDTO.getApartment().getName()).append("</td></tr>");
        html.append("<tr><th>Reservation Period</th><td>")
                .append(reservationDTO.getArrival()).append(" to ")
                .append(reservationDTO.getDeparture()).append("</td></tr>");
        html.append("<tr><th>Number of Adults</th><td>").append(reservationDTO.getAdults()).append("</td></tr>");
        html.append("<tr><th>Number of Children</th><td>").append(reservationDTO.getChildren()).append("</td></tr>");
        html.append("<tr><th>Total Price</th><td>").append(reservationDTO.getPrice()).append("</td></tr>");

        // Apartment Features
        html.append("<tr><th>Apartment Features</th><td>");
        for (ApartmentOption option : apartmentOptionList) {
            html.append(option.getDescription()).append(", ");
        }
        html.append("</td></tr>");

        html.append("</table>");

        // Payment Terms
        html.append("<h2>Payment Terms</h2>");
        html.append("<p>").append(paymentTerms).append("</p>");

        html.append("</body></html>");

        return convertHTMLToByteArray(html.toString());
    }

        private static byte[] convertHTMLToByteArray(String html) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(html);
                renderer.layout();
                renderer.createPDF(outputStream);
                return outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new byte[0];
        }

    private static String generateInvoiceNumber() {
        // Generate invoice number logic here (e.g., based on date, sequence, etc.)
        return "INV-123456";
    }

    private static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    }

