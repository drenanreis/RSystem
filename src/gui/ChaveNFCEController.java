package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.service.SrqNfceService;

public class ChaveNFCEController implements Initializable {

	private SrqNfceService service;
	
	@FXML
	private TextField txtNumero;
	
	@FXML
	private TextField txtSerie;
	
	@FXML
	private TextField txtChave;
	
	@FXML
	private Button btProcurar;
	
	@FXML
	public void onBtProcurarAction() {
		System.out.println("onBtProcurarAction");
	}
	
	public void setSrqNfceService(SrqNfceService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtNumero);
		Constraints.setTextFieldMaxLength(txtNumero, 10);
		Constraints.setTextFieldInteger(txtSerie);
		Constraints.setTextFieldMaxLength(txtSerie, 3);
		Constraints.setTextFieldInteger(txtChave);
		Constraints.setTextFieldMaxLength(txtChave, 44);
	}
}
