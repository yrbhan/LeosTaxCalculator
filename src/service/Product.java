package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.InvoiceService.DECIMAL_PLACES;

/* POJO to store the product details */
public class Product {
    private final String name;
    private final int qty;
    private final BigDecimal price;
    private final BigDecimal basicTaxPercent;
    private final BigDecimal importDutyPercent;
    private final boolean isBasicTaxExempted;
    private final boolean isImported;

    private BigDecimal totalPrice;
    private BigDecimal totalTaxAmount = new BigDecimal("0.00");

    public Product(String name, int qty, BigDecimal price, boolean isBasicTaxExempted, BigDecimal basicTaxPercent, boolean isImported, BigDecimal importDutyPercent) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.isBasicTaxExempted = isBasicTaxExempted;
        this.basicTaxPercent = basicTaxPercent;
        this.isImported = isImported;
        this.importDutyPercent = importDutyPercent;
        this.totalPrice = price.multiply(BigDecimal.valueOf(qty));

        if (!isBasicTaxExempted || isImported) {
            calculateTotalPriceAndTax();
        }
    }

    private void calculateTotalPriceAndTax() {
        if (!isBasicTaxExempted) {
            totalTaxAmount = totalTaxAmount.add(getTotalTaxAmount(basicTaxPercent));
        }

        if (isImported) {
            totalTaxAmount = totalTaxAmount.add(getTotalTaxAmount(importDutyPercent));
        }

        totalTaxAmount = BigDecimal.valueOf(Math.ceil(totalTaxAmount.doubleValue() * 20) / 20);
        totalTaxAmount = totalTaxAmount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        totalPrice = totalPrice.add(totalTaxAmount);
    }

    private BigDecimal getTotalTaxAmount(BigDecimal taxPercent) {
        return ((price.multiply(taxPercent)).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(qty));
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }
}
