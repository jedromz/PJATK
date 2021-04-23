package myInvoices;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {
    @FXML
    private Button goToCompany;
    @FXML
    private Button goToCustomer;
    @FXML
    private Button goToSummary;
    @FXML
    private Button goToInvoice;
    @FXML
    private Label companyName;
    @FXML
    private Label companyNIP;
    @FXML
    private Label companyAddress;
    @FXML
    private Label companyCountry;
    @FXML
    private Label companyBankAcc;
    @FXML
    private Label customerName,customerNIP,customerAddress,customerCountry;
    @FXML
    private Label totalCost,totalVat,paymentDue,paymentMethod;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->goToSummary.requestFocus());
        try {
            refreshDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void changeToCompany(ActionEvent event) throws IOException {
        DataTracker.getInstance().changeToCompany(event);
    }

    public void changeToCustomer(ActionEvent event) throws IOException {
        DataTracker.getInstance().changeToCustomer(event);
    }


    public void changeToInvoice(ActionEvent event) throws IOException {
        DataTracker.getInstance().changeToInvoice(event);
    }
    @FXML
    public void refreshDetails() throws IOException {
        FXMLLoader companyloader = new FXMLLoader(getClass().getResource("/company.fxml"));
        FXMLLoader customerloader = new FXMLLoader(getClass().getResource("/customer.fxml"));
        Parent companyParent = (Parent)companyloader.load();
        Parent customerParent = (Parent)customerloader.load();
        CompanyController companyController = companyloader.getController();
        CustomerController customerController = customerloader.getController();

        FXMLLoader invoiceloader = new FXMLLoader(getClass().getResource("/invoice.fxml"));
        Parent invoiceParent = (Parent)invoiceloader.load();
        InvoiceController invoiceController = invoiceloader.getController();
        companyName.setText(DataTracker.getInstance().getInvoice().getCompany().getName());
        companyNIP.setText(DataTracker.getInstance().getInvoice().getCompany().getNip());
        companyAddress.setText(DataTracker.getInstance().getInvoice().getCompany().getAddress());
        companyCountry.setText(DataTracker.getInstance().getInvoice().getCompany().getCountry());
        companyBankAcc.setText(DataTracker.getInstance().getInvoice().getCompany().getBankAcc());
        customerName.setText(DataTracker.getInstance().getInvoice().getCustomer().getName());
        customerNIP.setText(DataTracker.getInstance().getInvoice().getCustomer().getNip());
        customerAddress.setText(DataTracker.getInstance().getInvoice().getCustomer().getAddress());
        customerCountry.setText(DataTracker.getInstance().getInvoice().getCustomer().getCountry());;


        double priceTotal = 0;
        double vatTotal = 0;
        for(InvoiceItem item : DataTracker.getInstance().getInvoice().getInvoiceItems()){
            priceTotal+=Double.parseDouble(item.getTotalBrutto());
            vatTotal+=Double.parseDouble(item.getTotalVat());

        }
        DecimalFormat df = new DecimalFormat("0.00##");
        totalCost.setText(df.format(priceTotal));
        totalVat.setText(df.format(vatTotal));
        paymentDue.setText(DataTracker.getInstance().getPaymentDate().toString());
        paymentMethod.setText(DataTracker.getInstance().getFormOfPayment());
        
    }
}
