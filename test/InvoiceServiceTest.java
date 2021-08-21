import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.InvoiceService;
import service.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceServiceTest {

    @ParameterizedTest
    @MethodSource("getProductList")
    void calculateTotalInvoiceTest(List<Product> productList, List<BigDecimal> expectedPriceOfEachProduct, BigDecimal expectedTotalPrice, BigDecimal expectedTotalTax) {
        InvoiceService invoiceService = new InvoiceService();
        invoiceService.calculateReceipt(productList);

        assertEquals(expectedPriceOfEachProduct, productList.stream().map(Product::getTotalPrice).collect(Collectors.toList()));
        assertEquals(expectedTotalPrice, invoiceService.getFinalAmount());
        assertEquals(expectedTotalTax, invoiceService.getTotalTax());
    }




    private static Stream<Arguments> getProductList() {
        List<Product> productList1 = new ArrayList<>();
        productList1.add(new Product("book", 1, new BigDecimal("124.99"), true, new BigDecimal("10"), false, new BigDecimal("5")));
        productList1.add(new Product("music CD", 1, new BigDecimal("149.99"), false, new BigDecimal("10"), false, new BigDecimal("5")));
        productList1.add(new Product("chocolate bar", 1, new BigDecimal("40.85"), true, new BigDecimal("10"), false, new BigDecimal("5")));

        List<BigDecimal> expectedPriceOfEachProductInList1 = new ArrayList<>();
        expectedPriceOfEachProductInList1.add(new BigDecimal("124.99"));
        expectedPriceOfEachProductInList1.add(new BigDecimal("164.99"));
        expectedPriceOfEachProductInList1.add(new BigDecimal("40.85"));

        List<Product> productList2 = new ArrayList<>();
        productList2.add(new Product("imported chocolates", 1, new BigDecimal("100.00"), true, new BigDecimal("10"), true, new BigDecimal("5")));
        productList2.add(new Product("imported perfume", 1, new BigDecimal("470.50"), false, new BigDecimal("10"), true, new BigDecimal("5")));

        List<BigDecimal> expectedPriceOfEachProductInList2 = new ArrayList<>();
        expectedPriceOfEachProductInList2.add(new BigDecimal("105.00"));
        expectedPriceOfEachProductInList2.add(new BigDecimal("541.10"));

        List<Product> productList3 = new ArrayList<>();
        productList3.add(new Product("imported perfume", 1, new BigDecimal("270.99"), false, new BigDecimal("10"), true, new BigDecimal("5")));
        productList3.add(new Product("perfume", 1, new BigDecimal("180.99"), false, new BigDecimal("10"), false, new BigDecimal("5")));
        productList3.add(new Product("headache pills", 1, new BigDecimal("19.75"), true, new BigDecimal("10"), false, new BigDecimal("5")));
        productList3.add(new Product("imported chocolates", 1, new BigDecimal("210.25"), true, new BigDecimal("10"), true, new BigDecimal("5")));

        List<BigDecimal> expectedPriceOfEachProductInList3 = new ArrayList<>();
        expectedPriceOfEachProductInList3.add(new BigDecimal("311.64"));
        expectedPriceOfEachProductInList3.add(new BigDecimal("199.09"));
        expectedPriceOfEachProductInList3.add(new BigDecimal("19.75"));
        expectedPriceOfEachProductInList3.add(new BigDecimal("220.80"));

        return Stream.of(
                Arguments.of(productList1, expectedPriceOfEachProductInList1, new BigDecimal("330.83"), new BigDecimal("15.00"))
                , Arguments.of(productList2, expectedPriceOfEachProductInList2, new BigDecimal("646.10"), new BigDecimal("75.60"))
                , Arguments.of(productList3, expectedPriceOfEachProductInList3, new BigDecimal("751.28"), new BigDecimal("69.30"))
        );
    }
}
