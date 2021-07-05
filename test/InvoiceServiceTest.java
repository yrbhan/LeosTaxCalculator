import static org.junit.Assert.assertEquals;

import dto.InvoiceDetails;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.InvoiceService;

public class InvoiceServiceTest {

	@CsvSource({
			"ABC,	2, 		3, 		35, 	5, 		11800.0,		35400.0,		0.0, 		560.0, 		47760.0, 		7260.0",
			"ABC, 	0, 		1, 		50, 	10, 	0.0, 			11800.0, 		9440.0,		1120.0, 	22360.0, 		3360.0",
			"ABC, 	2, 		0, 		0, 		30, 	11800.0,		0.0, 			0.0,		3360.0, 	15160.0, 		2160.0"
	})
	@ParameterizedTest
	public void calculateMonthlyInvoiceTest(String companyName, Integer openSeats, Integer cabinSeats, Integer conferenceHours, Integer meals,
											Double totalOpenSeatsPrice, Double totalCabinSeatsPrice, Double totalConferencePrice, Double totalMealsPrice,
											Double totalBill, Double totalGST) {


		InvoiceDetails invoiceDetails = new InvoiceDetails(companyName, openSeats, cabinSeats, conferenceHours, meals);
		InvoiceService.calculateMonthlyInvoice(invoiceDetails);

		assertEquals(totalOpenSeatsPrice, invoiceDetails.getTotalOpenSeatsPrice());
		assertEquals(totalCabinSeatsPrice, invoiceDetails.getTotalCabinSeatsPrice());
		assertEquals(totalConferencePrice, invoiceDetails.getTotalConferenceHoursPrice());
		assertEquals(totalMealsPrice, invoiceDetails.getTotalMealsPrice());
		assertEquals(totalMealsPrice, invoiceDetails.getTotalMealsPrice());
		assertEquals(totalBill, invoiceDetails.getTotalBill());
		assertEquals(totalGST, invoiceDetails.getTotalGST());
	}
}
