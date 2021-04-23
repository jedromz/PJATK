package myInvoices;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class InvoiceController implements Initializable {
    int itemNumber;
    @FXML
    private Button goToCustomer,goToInvoice,goToSummary,goToCompany;
    @FXML
    private ImageView invoiceImg;
    @FXML
    private JFXButton addRow;
    @FXML
    private JFXButton savetoFile,readFromfile;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private TextField itemNo;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField itemPricePerUnit;
    @FXML
    private TextField itemTotalNetto;
    @FXML
    private TextField itemVat;
    @FXML
    private TextField itemTotalVat;
    @FXML
    private TextField itemTotalBrutto,defaultTaxRate,filePathTextField;
    @FXML
    private TableView<InvoiceItem> invoiceItemTableView;
    @FXML
    private DatePicker paymentDatePicker,issueDatePicker;
    @FXML
    private JFXComboBox<String> formOfPayment;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int size = DataTracker.getInstance().getInvoice().getInvoiceItems().size();
        switch (size){
            case 0:
                itemNumber=1;
                break;
            case 1:
                itemNumber=1;
                break;
            default:
                itemNumber=size+1;
                break;
        }
        itemName.setPromptText("Item Name");
        itemQuantity.setPromptText("Quantity");
        itemPricePerUnit.setPromptText("Price per Unit");
        itemVat.setPromptText("VAT[%]");
        Platform.runLater(()->goToInvoice.requestFocus());
        invoiceItemTableView.setEditable(true);
        TableColumn<InvoiceItem,String> numberColumn = new TableColumn<>("No");
        TableColumn<InvoiceItem,String> nameColumn = new TableColumn<>("Item Name");
        TableColumn<InvoiceItem,String> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<InvoiceItem,String> pricePerUnitColumn = new TableColumn<>("Price/Unit");
        TableColumn<InvoiceItem,String> totalNettoColumn = new TableColumn<>("Total Netto");
        TableColumn<InvoiceItem,String> vatColumn = new TableColumn<>("VAT[%]");
        TableColumn<InvoiceItem,String> totalVatColumn = new TableColumn<>("Total VAT");
        TableColumn<InvoiceItem,String> totalBruttoColumn = new TableColumn<>("Total Brutto");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
        totalNettoColumn.setCellValueFactory(new PropertyValueFactory<>("totalNetto"));
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vatPerc"));
        totalVatColumn.setCellValueFactory(new PropertyValueFactory<>("totalVat"));
        totalBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("totalBrutto"));
        
        invoiceItemTableView.getColumns().add(numberColumn);
        invoiceItemTableView.getColumns().add(nameColumn);
        invoiceItemTableView.getColumns().add(quantityColumn);
        invoiceItemTableView.getColumns().add(pricePerUnitColumn);
        invoiceItemTableView.getColumns().add(totalNettoColumn);
        invoiceItemTableView.getColumns().add(vatColumn);
        invoiceItemTableView.getColumns().add(totalVatColumn);
        invoiceItemTableView.getColumns().add(totalBruttoColumn);

        formOfPayment.getItems().add("Bank Transfer");
        formOfPayment.getItems().add("Cash");

        issueDatePicker.setValue(DataTracker.getInstance().getIssueDate());
        paymentDatePicker.setValue(DataTracker.getInstance().getPaymentDate());


    }
    public void initData(Invoice invoice){
        invoiceItemTableView.getItems().setAll(DataTracker.getInstance().getInvoice().getInvoiceItems());
    }

    public void changeToCustomer(ActionEvent event) throws IOException {
        DataTracker.getInstance().changeToCustomer(event);
    }
    public void changeToCompany(ActionEvent event) throws IOException {
       DataTracker.getInstance().changeToCompany(event);
    }
    public void changeToSummary(ActionEvent event) throws IOException {
       DataTracker.getInstance().changeToSummary(event);
    }
    @FXML
    public void addRowToTableView(){
        DecimalFormat df = new DecimalFormat("0.00##");
        String quantity=itemQuantity.getText();
        String vat = itemVat.getText();
        String pricePerUnit=itemPricePerUnit.getText();
        String totalNetto =null;
        String totalVat =null;
        String totalBrutto =null;

            if(vat.isEmpty())
                vat=defaultTaxRate.getText();
            if (checkIfValid(vat, quantity, pricePerUnit)) {

                    totalNetto = String.format(String.valueOf(Double.parseDouble(pricePerUnit) * Double.parseDouble(quantity)), df);
                    totalVat = String.format(String.valueOf((Double.parseDouble(pricePerUnit) * Double.parseDouble(vat) / 100) * Double.parseDouble(quantity)), df);
                    totalBrutto = String.format(String.valueOf(Double.parseDouble(totalNetto) + Double.parseDouble(totalVat)), df);



                    invoiceItemTableView.getItems().add(new InvoiceItem(String.valueOf(itemNumber), itemName.getText(), quantity, pricePerUnit, totalNetto, vat, totalVat, totalBrutto));

                itemNumber++;
            }

        itemName.clear();
        itemQuantity.clear();
        itemPricePerUnit.clear();
        itemVat.clear();
    }
    public void deleteSelectedRow(){
        if(invoiceItemTableView.getSelectionModel().getSelectedItem() != null) {
            for (InvoiceItem item : invoiceItemTableView.getItems()) {
                if (Integer.parseInt(item.getNumber()) > Double.parseDouble(invoiceItemTableView.getSelectionModel().getSelectedItem().getNumber())) {
                    item.setNumber(String.valueOf(Integer.parseInt(item.getNumber()) - 1));
                }

            }
        itemNumber--;
        }
        invoiceItemTableView.getItems().removeAll(invoiceItemTableView.getSelectionModel().getSelectedItems());
    }
    @FXML
    public void onSaveButton(){

        DataTracker.getInstance().getInvoice().setInvoiceItems(invoiceItemTableView.getItems());
        LocalDate pdate = paymentDatePicker.getValue();
        LocalDate idate = issueDatePicker.getValue();
        if(idate.isBefore(pdate)) {

            DataTracker.getInstance().setPaymentDate(pdate);
        }




    }
    boolean checkIfValid(String quantity,String ppu,String vat){
        Pattern regexTwoDecimals = Pattern.compile("^[0-9]*\\.?[0-9]{1,2}|^$");
        boolean allAreNumbers = false;
        if(! (quantity.isEmpty() && ppu.isEmpty() && vat.isEmpty())) {
            if (regexTwoDecimals.matcher(vat).matches() && regexTwoDecimals.matcher(quantity).matches() && regexTwoDecimals.matcher(ppu).matches())
                allAreNumbers = true;
        }
        return allAreNumbers;
    }

    public DatePicker getDatePicker() {
        return paymentDatePicker;
    }

    public void onComboBoxSelected(){
        DataTracker.getInstance().setFormOfPayment(formOfPayment.getValue());
    }
    public void onSaveToFile(){
        if(!(filePathTextField.getText().isEmpty()) && DataTracker.getInstance().getInvoice() != null) {

            BufferedWriter writer = null;
            try {
                XmlMapper mapper = new XmlMapper();
                String data = mapper.writeValueAsString(DataTracker.getInstance().getInvoice());
                writer = new BufferedWriter(new FileWriter(filePathTextField.getText() + ".invoice"));
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void onReadFromFile(){
        if(!(filePathTextField.getText().isEmpty())) {
            XmlMapper mapper = new XmlMapper();
            try {
                DataTracker.getInstance().setInvoice(mapper.readValue(new File(filePathTextField.getText() + ".invoice"), Invoice.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
