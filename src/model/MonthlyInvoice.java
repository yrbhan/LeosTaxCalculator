package model;
/**
 * POJO to store the invoice details
 */
public class MonthlyInvoice {
    private String companyName;
    private Integer openSeatsCount;
    private Integer cabinSeatsCount;
    private Integer mealsCount;
    private Integer conferenceRoomHours;

    Double totalOpenSeatsPrice = 0.00;
    Double totalCabinSeatsPrice = 0.00;
    Double totalConferenceHoursPrice = 0.00;
    Double totalMealsPrice = 0.00;
    int netConferenceHours = 0;

    Double totalBill = 0.00;
    Double totalGST = 0.00;

    public MonthlyInvoice() {

    }

    public MonthlyInvoice(String companyName, Integer openSeatsCount, Integer cabinSeatsCount, Integer conferenceRoomHours, Integer mealsCount) {
        this.companyName = companyName;
        this.openSeatsCount = openSeatsCount;
        this.cabinSeatsCount = cabinSeatsCount;
        this.conferenceRoomHours = conferenceRoomHours;
        this.mealsCount = mealsCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOpenSeatsCount() {
        return openSeatsCount;
    }

    public void setOpenSeatsCount(Integer openSeatsCount) {
        this.openSeatsCount = openSeatsCount;
    }

    public Integer getCabinSeatsCount() {
        return cabinSeatsCount;
    }

    public void setCabinSeatsCount(Integer cabinSeatsCount) {
        this.cabinSeatsCount = cabinSeatsCount;
    }

    public Integer getMealsCount() {
        return mealsCount;
    }

    public void setMealsCount(Integer mealsCount) {
        this.mealsCount = mealsCount;
    }

    public Integer getConferenceRoomHours() {
        return conferenceRoomHours;
    }

    public void setConferenceRoomHours(Integer conferenceRoomHours) {
        this.conferenceRoomHours = conferenceRoomHours;
    }

    public Double getTotalOpenSeatsPrice() {
        return totalOpenSeatsPrice;
    }

    public void setTotalOpenSeatsPrice(Double totalOpenSeatsPrice) {
        this.totalOpenSeatsPrice = totalOpenSeatsPrice;
    }

    public Double getTotalCabinSeatsPrice() {
        return totalCabinSeatsPrice;
    }

    public void setTotalCabinSeatsPrice(Double totalCabinSeatsPrice) {
        this.totalCabinSeatsPrice = totalCabinSeatsPrice;
    }

    public Double getTotalConferenceHoursPrice() {
        return totalConferenceHoursPrice;
    }

    public void setTotalConferenceHoursPrice(Double totalConferenceHoursPrice) {
        this.totalConferenceHoursPrice = totalConferenceHoursPrice;
    }

    public Double getTotalMealsPrice() {
        return totalMealsPrice;
    }

    public void setTotalMealsPrice(Double totalMealsPrice) {
        this.totalMealsPrice = totalMealsPrice;
    }

    public int getNetConferenceHours() {
        return netConferenceHours;
    }

    public void setNetConferenceHours(int netConferenceHours) {
        this.netConferenceHours = netConferenceHours;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public Double getTotalGST() {
        return totalGST;
    }

    public void setTotalGST(Double totalGST) {
        this.totalGST = totalGST;
    }

    @Override
    public String toString() {
        StringBuilder finalInvoice = new StringBuilder();

        finalInvoice.append("\n\n**********Invoice for: ").append(getCompanyName()).append("**********\n\n");
        if(openSeatsCount > 0) {
            finalInvoice.append(getOpenSeatsCount()).append(" Open Seats: ").append(totalOpenSeatsPrice).append("\n");
        }
        if(cabinSeatsCount > 0) {
            finalInvoice.append(getCabinSeatsCount()).append(" Cabin Seats: ").append(totalCabinSeatsPrice).append("\n");
        }
        if(netConferenceHours > 0) {
            finalInvoice.append(getConferenceRoomHours()).append(" hours of Conference Room usage: ").append(totalConferenceHoursPrice).append("\n");
        } else {
            finalInvoice.append(getConferenceRoomHours()).append(" hours of Conference Room usage: ").append("0").append("\n");
        }
        if(mealsCount > 0) {
            finalInvoice.append(getMealsCount()).append(" meals: ").append(totalMealsPrice).append("\n");
        }
        finalInvoice.append("Total: ").append(totalBill).append("\n");
        finalInvoice.append("Total GST: ").append(totalGST).append("\n");

        return finalInvoice.toString();
    }
}
