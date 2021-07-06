package service;

import model.MonthlyInvoice;
import org.apache.commons.math3.util.Precision;

import static constants.InvoiceConstants.*;

public class InvoiceService {

    /**
     * Calculate and display the final invoice for the month
     *
     * @param monthlyInvoice
     */
    public MonthlyInvoice calculateMonthlyInvoice(MonthlyInvoice monthlyInvoice) {

        double totalOpenSeatsPrice = 0.00;
        double totalCabinSeatsPrice = 0.00;
        double totalConferenceHoursPrice = 0.00;
        double totalMealsPrice = 0.00;
        int totalFreeConferenceHours = 0;
        int netConferenceHours;

        double totalBill = 0.00;
        double totalGST = 0.00;

        if(monthlyInvoice.getOpenSeatsCount() > 0) {
            totalOpenSeatsPrice = calculateTotalCharge(monthlyInvoice.getOpenSeatsCount(), OPEN_SEAT_PRICE_PER_SEAT, (SEATS_CONFERENCE_GST + 1));
            totalFreeConferenceHours = calculateFreeConferenceHours(monthlyInvoice.getOpenSeatsCount(), FREE_CONFERENCE_HOURS_PER_OPEN_SEAT);
            totalGST = monthlyInvoice.getOpenSeatsCount() * OPEN_SEAT_PRICE_PER_SEAT * SEATS_CONFERENCE_GST;

            monthlyInvoice.setTotalOpenSeatsPrice(Precision.round(totalOpenSeatsPrice,2));
        }

        if(monthlyInvoice.getCabinSeatsCount() > 0) {
            totalCabinSeatsPrice = calculateTotalCharge(monthlyInvoice.getCabinSeatsCount(), CABIN_SEAT_PRICE_PER_SEAT, (SEATS_CONFERENCE_GST + 1));
            totalFreeConferenceHours += calculateFreeConferenceHours(monthlyInvoice.getCabinSeatsCount(), FREE_CONFERENCE_HOURS_PER_CABIN_SEAT);
            totalGST += (monthlyInvoice.getCabinSeatsCount() * CABIN_SEAT_PRICE_PER_SEAT * SEATS_CONFERENCE_GST);

            monthlyInvoice.setTotalCabinSeatsPrice(Precision.round(totalCabinSeatsPrice, 2));
        }

        if(monthlyInvoice.getConferenceRoomHours() > 0) {
            netConferenceHours = monthlyInvoice.getConferenceRoomHours() - totalFreeConferenceHours;
            if(netConferenceHours > 0) {
                totalConferenceHoursPrice = calculateTotalCharge(netConferenceHours, CONFERENCE_ROOM_HOURLY_PRICE, (SEATS_CONFERENCE_GST + 1));
                totalGST += calculateTotalCharge(netConferenceHours, CONFERENCE_ROOM_HOURLY_PRICE, SEATS_CONFERENCE_GST);

                monthlyInvoice.setTotalConferenceHoursPrice(Precision.round(totalConferenceHoursPrice, 2));
                monthlyInvoice.setNetConferenceHours(netConferenceHours);
            } else {
                monthlyInvoice.setTotalConferenceHoursPrice(0.00);
                monthlyInvoice.setNetConferenceHours(0);
            }
        }

        if(monthlyInvoice.getMealsCount() > 0) {
            totalMealsPrice = calculateTotalCharge(monthlyInvoice.getMealsCount(), MEAL_PRICE, (MEALS_GST + 1));
            totalGST += calculateTotalCharge(monthlyInvoice.getMealsCount(), MEAL_PRICE, MEALS_GST);

            monthlyInvoice.setTotalMealsPrice(Precision.round(totalMealsPrice, 2));
        }

        totalBill = totalOpenSeatsPrice + totalCabinSeatsPrice + totalConferenceHoursPrice + totalMealsPrice;
        monthlyInvoice.setTotalGST(Precision.round(totalGST, 2));
        monthlyInvoice.setTotalBill(Precision.round(totalBill, 2));

        return monthlyInvoice;
    }

    private double calculateTotalCharge(int count, int pricePerUnit, Double gst) {
        return count * pricePerUnit * gst;
    }

    private int calculateFreeConferenceHours(int count, int freeHoursPerUnit){
        return count * freeHoursPerUnit;
    }


}
