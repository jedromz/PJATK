package myInvoices;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {
    @FXML
    private JFXTextField customerName;
    @FXML
    private JFXTextField customerNip;
    @FXML
    private JFXTextField customerAddress;
    @FXML
    private JFXTextField customerCountry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public JFXTextField getCustomerName() {
        return customerName;
    }

    public JFXTextField getCustomerNip() {
        return customerNip;
    }

    public JFXTextField getCustomerAddress() {
        return customerAddress;
    }

    public JFXTextField getCustomerCountry() {
        return customerCountry;
    }
}
