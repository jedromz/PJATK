package myInvoices;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataTracker {
    private Company company;
    private Customer customer;
    private Invoice invoice;
    private List<InvoiceItem> invoiceItems;
    private String dateOfPayment;
    private String formOfPayment;
    private LocalDate issueDate = LocalDate.now();
    private LocalDate paymentDate = LocalDate.now();
    private LocalDate Date = LocalDate.now();

    private DataTracker(){
        company = new Company("To Fill","To Fill","To Fill","To Fill","To Fill");
        customer = new Customer("Bill To","To Fill","To Fill","To Fill");
        invoiceItems = new ArrayList<>();
        invoice = new Invoice(company,customer,invoiceItems);
    }
    private static DataTracker instance;


    public static DataTracker getInstance() {
        if (instance == null) {
            instance = new DataTracker();
        }
        return instance;
    }

    public Company getCompany() {
        return company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        invoiceItems=invoice.getInvoiceItems();
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public static void setInstance(DataTracker instance) {
        DataTracker.instance = instance;
    }

    public static void changeToCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DataTracker.class.getResource("/customer.fxml"));
        Parent customerParent = loader.load();

        Scene customerScene = new Scene(customerParent);
        customerScene.getStylesheets().add("/style.css");

        CustomerController controller = loader.getController();
        controller.initData(DataTracker.getInstance().getInvoice().getCustomer());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(customerScene);

        window.show();
    }
    public static void changeToInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DataTracker.class.getResource("/invoice.fxml"));
        Parent invoiceParent = loader.load();

        Scene invoiceScene = new Scene(invoiceParent);
        invoiceScene.getStylesheets().add("/style.css");

        InvoiceController controller = loader.getController();
        controller.initData(DataTracker.getInstance().getInvoice());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(invoiceScene);

        window.show();
    }
    public static void changeToSummary(ActionEvent event) throws IOException {
        Parent summaryParent = FXMLLoader.load(DataTracker.class.getResource("/summary.fxml"));
        Scene summaryScene = new Scene(summaryParent);
        summaryScene.getStylesheets().add("/style.css");

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(summaryScene);
        window.show();
    }
    public static void changeToCompany(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DataTracker.class.getResource("/company.fxml"));
        Parent companyParent = loader.load();

        Scene companyScene = new Scene(companyParent);
        companyScene.getStylesheets().add("/style.css");

        CompanyController controller = loader.getController();
        controller.initData();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();


        window.setScene(companyScene);
        window.show();

    }



    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
