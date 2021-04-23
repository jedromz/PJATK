package myInvoices;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {

    @FXML
    private Button goToCustomer,goToInvoice,goToSummary,goToCompany,editCompanyButton;
    @FXML
    private ImageView companyImg;
    @FXML
    private Label compName,compNip,compAddress,compCountry,compBankAcc;
    Company myCompany;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myCompany = DataTracker.getInstance().getInvoice().getCompany();
        Platform.runLater(()->goToCompany.requestFocus());
        companyImg.setImage(new Image("assets/copmany.png"));
        compName.setText("Company Name");
        editCompanyButton.setOnAction(event -> {
            try {
                openEditCompanyWindow();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void initData(){
        Company myCompany = DataTracker.getInstance().getInvoice().getCompany();
        compName.setText(myCompany.getName());
        compNip.setText(myCompany.getNip());
        compAddress.setText(myCompany.getAddress());
        compCountry.setText(myCompany.getCountry());
    }
    @FXML
    private void openEditCompanyWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editCompany.fxml"));
        Parent editCompanyParent = (Parent)loader.load();
        EditCompanyController editCompanyController = loader.getController();
        Scene scene = new Scene(editCompanyParent);
        scene.getStylesheets().add("/style.css");
        Stage window = new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
        if(editCompanyController.isSaved()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    compName.setText(editCompanyController.getNameTextField().getText());
                    compNip.setText(editCompanyController.getNipTextField().getText());
                    compAddress.setText(editCompanyController.getAddressTextField().getText());
                    compCountry.setText(editCompanyController.getCountryTextField().getText());
                    compBankAcc.setText(editCompanyController.getBankAccTextField().getText());
                    DataTracker.getInstance().getInvoice().setCompany(new Company(compName.getText(),compNip.getText(),compAddress.getText(),compCountry.getText(),compBankAcc.getText()));
                }
            });
        }

    }

    public void changeToCustomer(ActionEvent event) throws IOException {
       DataTracker.getInstance().changeToCustomer(event);
    }
    public void changeToInvoice(ActionEvent event) throws IOException {
       DataTracker.getInstance().changeToInvoice(event);
    }

    public void changeToSummary(ActionEvent event) throws IOException {
       DataTracker.getInstance().changeToSummary(event);
    }


}

