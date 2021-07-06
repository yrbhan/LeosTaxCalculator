import model.MonthlyInvoice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.InvoiceService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceServiceTest {

	@CsvSource({
			"ABC,	2, 		3, 		35, 	5, 		11800.0,		35400.0,		0.0, 		560.0, 		47760.0, 		7260.0",
			"ABC, 	0, 		1, 		50, 	10, 	0.0, 			11800.0, 		9440.0,		1120.0, 	22360.0, 		3360.0",
			"ABC, 	2, 		0, 		0, 		30, 	11800.0,		0.0, 			0.0,		3360.0, 	15160.0, 		2160.0"
	})
	@ParameterizedTest
	void calculateMonthlyInvoiceTest(String companyName, Integer openSeats, Integer cabinSeats, Integer conferenceHours, Integer meals,
											Double totalOpenSeatsPrice, Double totalCabinSeatsPrice, Double totalConferencePrice, Double totalMealsPrice,
											Double totalBill, Double totalGST) {


		MonthlyInvoice monthlyInvoice = new MonthlyInvoice(companyName, openSeats, cabinSeats, conferenceHours, meals);
		InvoiceService invoiceService = new InvoiceService();
		invoiceService.calculateMonthlyInvoice(monthlyInvoice);

		assertEquals(totalOpenSeatsPrice, monthlyInvoice.getTotalOpenSeatsPrice());
		assertEquals(totalCabinSeatsPrice, monthlyInvoice.getTotalCabinSeatsPrice());
		assertEquals(totalConferencePrice, monthlyInvoice.getTotalConferenceHoursPrice());
		assertEquals(totalMealsPrice, monthlyInvoice.getTotalMealsPrice());
		assertEquals(totalMealsPrice, monthlyInvoice.getTotalMealsPrice());
		assertEquals(totalBill, monthlyInvoice.getTotalBill());
		assertEquals(totalGST, monthlyInvoice.getTotalGST());
	}
}
