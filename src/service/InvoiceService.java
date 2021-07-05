package service;

import dto.InvoiceDetails;

import static dto.InvoiceDetails.*;

import java.util.*;
import org.apache.commons.math3.util.Precision;

public class InvoiceService {

    public static void main(String[] args) {
        InvoiceDetails invoiceDetails = new InvoiceDetails();

        Scanner sc = new Scanner(System.in);
        System.out.print("Company name: ");
        invoiceDetails.setCompanyName(sc.nextLine());

        System.out.print("Open seats required(enter 0 if not required): ");
        invoiceDetails.setOpenSeatsCount(sc.nextInt());

        System.out.print("Cabin seats required(enter 0 if not required): ");
        invoiceDetails.setCabinSeatsCount(sc.nextInt());

        System.out.print("Conference hours required(enter 0 if not required): ");
        invoiceDetails.setConferenceRoomHours(sc.nextInt());

        System.out.print("Meals required(enter 0 if not required): ");
        invoiceDetails.setMealsCount(sc.nextInt());

        calculateMonthlyInvoice(invoiceDetails);
    }

    /**
     * Calculate and display the final invoice for the month
     *
     * @param invoiceDetails
     */
    public static void calculateMonthlyInvoice(InvoiceDetails invoiceDetails) {

        double totalOpenSeatsPrice = 0.00;
        double totalCabinSeatsPrice = 0.00;
        double totalConferenceHoursPrice = 0.00;
        double totalMealsPrice = 0.00;
        int totalFreeConferenceHours = 0;
        int netConferenceHours;

        double totalBill;
        double totalGST = 0.00;

        if(invoiceDetails.getOpenSeatsCount() > 0) {
            totalOpenSeatsPrice = invoiceDetails.getOpenSeatsCount() * OPEN_SEAT_PRICE_PER_SEAT * (SEATS_CONFERENCE_GST + 1);
            totalFreeConferenceHours = invoiceDetails.getOpenSeatsCount() * FREE_CONFERENCE_HOURS_PER_OPEN_SEAT;
            totalGST = invoiceDetails.getOpenSeatsCount() * OPEN_SEAT_PRICE_PER_SEAT * SEATS_CONFERENCE_GST;

            invoiceDetails.setTotalOpenSeatsPrice(Precision.round(totalOpenSeatsPrice,2));
        }

        if(invoiceDetails.getCabinSeatsCount() > 0) {
            totalCabinSeatsPrice = invoiceDetails.getCabinSeatsCount() * CABIN_SEAT_PRICE_PER_SEAT * (SEATS_CONFERENCE_GST + 1);
            totalFreeConferenceHours += invoiceDetails.getCabinSeatsCount() * FREE_CONFERENCE_HOURS_PER_CABIN_SEAT;
            totalGST += (invoiceDetails.getCabinSeatsCount() * CABIN_SEAT_PRICE_PER_SEAT * SEATS_CONFERENCE_GST);

            invoiceDetails.setTotalCabinSeatsPrice(Precision.round(totalCabinSeatsPrice, 2));
        }

        if(invoiceDetails.getConferenceRoomHours() > 0) {
            netConferenceHours = invoiceDetails.getConferenceRoomHours() - totalFreeConferenceHours;
            if(netConferenceHours > 0) {
                totalConferenceHoursPrice = netConferenceHours * CONFERENCE_ROOM_HOURLY_PRICE * (SEATS_CONFERENCE_GST + 1);
                totalGST += (netConferenceHours * CONFERENCE_ROOM_HOURLY_PRICE * SEATS_CONFERENCE_GST);

                invoiceDetails.setTotalConferenceHoursPrice(Precision.round(totalConferenceHoursPrice, 2));
                invoiceDetails.setNetConferenceHours(netConferenceHours);
            } else {
                invoiceDetails.setTotalConferenceHoursPrice(0.00);
                invoiceDetails.setNetConferenceHours(0);
            }
        }

        if(invoiceDetails.getMealsCount() > 0) {
            totalMealsPrice = invoiceDetails.getMealsCount() * MEAL_PRICE * (MEALS_GST + 1);
            totalGST += (invoiceDetails.getMealsCount() * MEAL_PRICE * MEALS_GST);

            invoiceDetails.setTotalMealsPrice(Precision.round(totalMealsPrice, 2));
        }

        totalBill = totalOpenSeatsPrice + totalCabinSeatsPrice + totalConferenceHoursPrice + totalMealsPrice;
        invoiceDetails.setTotalGST(Precision.round(totalGST, 2));
        invoiceDetails.setTotalBill(Precision.round(totalBill, 2));

        System.out.println(invoiceDetails);
    }
}
