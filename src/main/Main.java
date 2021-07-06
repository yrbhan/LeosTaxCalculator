package main;

import model.MonthlyInvoice;
import service.InvoiceService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InvoiceService invoiceService = new InvoiceService();
        MonthlyInvoice monthlyInvoice = new MonthlyInvoice();

        Scanner sc = new Scanner(System.in);
        System.out.print("Company name: ");
        monthlyInvoice.setCompanyName(sc.nextLine());

        System.out.print("Open seats required(enter 0 if not required): ");
        monthlyInvoice.setOpenSeatsCount(sc.nextInt());

        System.out.print("Cabin seats required(enter 0 if not required): ");
        monthlyInvoice.setCabinSeatsCount(sc.nextInt());

        System.out.print("Conference hours required(enter 0 if not required): ");
        monthlyInvoice.setConferenceRoomHours(sc.nextInt());

        System.out.print("Meals required(enter 0 if not required): ");
        monthlyInvoice.setMealsCount(sc.nextInt());

        System.out.println(invoiceService.calculateMonthlyInvoice(monthlyInvoice));
    }
}
