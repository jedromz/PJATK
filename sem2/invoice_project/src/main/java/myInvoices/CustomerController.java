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

public class CustomerController implements Initializable {
    @FXML
    private Button goToCompany;
    @FXML
    private Button goToCustomer;
    @FXML
    private ImageView customerImg;
    @FXML
    private Button editCustomerButton;
    @FXML
    private Label customerName;
    @FXML
    private Label customerNip;
    @FXML
    private Label customerAddress;
    @FXML
    private Label customerCountry;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(()->goToCustomer.requestFocus());
        customerImg.setImage(new Image("assets/customer.png"));
        editCustomerButton.setOnAction(event -> {
            try {
                openEditCompanyWindow();
                goToCustomer.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public void initData(Customer customer){
        customerName.setText(customer.getName());
        customerNip.setText(customer.getNip());
        customerAddress.setText(customer.getAddress());
        customerCountry.setText(customer.getCountry());
    }
    public void changeToCompany(ActionEvent event) throws IOException {

      DataTracker.getInstance().changeToCompany(event);

    }
    public void changeToInvoice(ActionEvent event) throws IOException {

        DataTracker.getInstance().changeToInvoice(event);

    }
    public void changeToSummary(ActionEvent event) throws IOException {

        DataTracker.getInstance().changeToSummary(event);

    }

    private void openEditCompanyWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editCustomer.fxml"));
        Parent editCustomerParent = (Parent)loader.load();
        EditCustomerController editCustomerController = loader.getController();
        Scene scene = new Scene(editCustomerParent);
        scene.getStylesheets().add("/style.css");
        Stage window = new Stage();
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    customerName.setText(editCustomerController.getCustomerName().getText());
                    customerNip.setText(editCustomerController.getCustomerNip().getText());
                    customerAddress.setText(editCustomerController.getCustomerAddress().getText());
                    customerCountry.setText(editCustomerController.getCustomerCountry().getText());
                    DataTracker.getInstance().getInvoice().setCustomer(new Customer(customerName.getText(),customerNip.getText(),customerAddress.getText(),customerCountry.getText()));
                }
            });

    }

}
