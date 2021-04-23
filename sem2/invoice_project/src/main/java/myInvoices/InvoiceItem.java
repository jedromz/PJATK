package myInvoices;

public class InvoiceItem {
    private String number;
    private String itemName;
    private String quantity;
    private String pricePerUnit;
    private String totalNetto;
    private String vatPerc;
    private String totalVat;
    private String totalBrutto;

    public InvoiceItem(){}

    public InvoiceItem(String number, String itemName, String quantity, String pricePerUnit, String totalNetto, String vatPerc, String totalVat, String totalBrutto) {
        this.number = number;
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalNetto = totalNetto;
        this.vatPerc = vatPerc;
        this.totalVat = totalVat;
        this.totalBrutto = totalBrutto;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(String totalNetto) {
        this.totalNetto = totalNetto;
    }

    public String getVatPerc() {
        return vatPerc;
    }

    public void setVatPerc(String vatPerc) {
        this.vatPerc = vatPerc;
    }

    public String getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(String totalVat) {
        this.totalVat = totalVat;
    }

    public String getTotalBrutto() {
        return totalBrutto;
    }

    public void setTotalBrutto(String totalBrutto) {
        this.totalBrutto = totalBrutto;
    }
}
