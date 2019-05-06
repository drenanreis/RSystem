package gui;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.SrqNfce;
import model.exceptions.ValidationException;
import model.service.SrqNfceService;

public class ChaveNFCEController implements Initializable {

	private SrqNfce entity;
	
	private SrqNfceService service;
	
	@FXML
	private TextField txtNumero;
	
	@FXML
	private TextField txtSerie;
	
	@FXML
	private TextField txtChave;
	
	@FXML
	private Button btProcurar;
	
	@FXML Label labelError;
	
	@FXML
	public void onBtProcurarAction() {
		//if (entity == null)
			//throw new IllegalStateException("Entity was null");
		//if (service == null)
		//	throw new IllegalStateException("Service was null");

		try {
			entity = getFormData();
			
			SrqNfce obj = service.findByNumeroAndSerie(entity);
			
			txtChave.setText(obj.getChave());
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	public void setSrqNfce(SrqNfce entity) {
		this.entity = entity;
	}
	
	public void setSrqNfceService(SrqNfceService service) {
		this.service = service;
	}
	
	private SrqNfce getFormData() {
		entity = new SrqNfce();

		ValidationException exception = new ValidationException("Validation error");

		if (txtNumero.getText() == null || txtNumero.getText().trim().equals(""))
			exception.addError("numero", "Field can't be empty");
		
		if (txtSerie.getText() == null || txtSerie.getText().trim().equals("")) {
			exception.addError("serie", "Field can't be empty");
		}
		
		entity.setNumero(Utils.tryParseToInt(txtNumero.getText()));
		entity.setSerie(Utils.tryParseToInt(txtSerie.getText()));

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return entity;
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
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("name"))
			labelError.setText(errors.get("name"));
	}
}
