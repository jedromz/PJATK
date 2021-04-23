package myInvoices;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EditCompanyController implements Initializable {

    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField nipTextField;
    @FXML
    private JFXTextField addressTextField;
    @FXML
    private JFXTextField countryTextField;
    @FXML
    private JFXTextField bankAccTextField;
    @FXML
    private JFXButton saveButton;

    private boolean saved;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saved=false;
        saveButton.setOnAction( event -> {
            if(checkEnteredData()) {
                saved = true;
            }
        });
    }
    boolean checkEnteredData(){
        Pattern nipRegex= Pattern.compile("^[1-9][0-9]{9}$");
        Pattern bankRegex = Pattern.compile("^[1-9][0-9]{25}$");
        return nipRegex.matcher(nipTextField.getText()).matches() &&  bankRegex.matcher(bankAccTextField.getText()).matches();
    }

    public JFXTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JFXTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JFXTextField getNipTextField() {
        return nipTextField;
    }

    public void setNipTextField(JFXTextField nipTextField) {
        this.nipTextField = nipTextField;
    }

    public JFXTextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(JFXTextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    public JFXTextField getCountryTextField() {
        return countryTextField;
    }

    public void setCountryTextField(JFXTextField countryTextField) {
        this.countryTextField = countryTextField;
    }

    public JFXTextField getBankAccTextField() {
        return bankAccTextField;
    }

    public void setBankAccTextField(JFXTextField bankAccTextField) {
        this.bankAccTextField = bankAccTextField;
    }

    public boolean isSaved() {
        return saved;
    }
}