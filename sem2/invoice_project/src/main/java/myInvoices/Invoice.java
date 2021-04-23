package myInvoices;

import java.util.List;

public class Invoice {
    private Company company=null;
    private Customer customer=null;
    private List<InvoiceItem> invoiceItems=null;

    public Invoice(){}

    public Invoice(Company company, Customer customer, List<InvoiceItem> invoiceItems) {
        this.company = company;
        this.customer = customer;
        this.invoiceItems = invoiceItems;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
