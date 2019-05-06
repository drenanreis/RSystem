package gui;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
import model.exceptions.Exceptions;
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
	private TextField txtValor;
	
	@FXML
	private TextField txtChave;
	
	@FXML
	private Button btProcurar;
	
	@FXML
	private Button btCopiarChave;
	
	@FXML Label labelError;
	
	@FXML
	public void onBtProcurarAction() {
		/*
		if (entity == null)
			throw new IllegalStateException("Entity was null");
		if (service == null)
			throw new IllegalStateException("Service was null");
		 */

		try {
			SrqNfce obj = getFormData();
			obj = service.findByNumeroAndSerie(entity);
		
			txtNumero.setText("");
			txtSerie.setText("");
			txtValor.setText(String.valueOf("R$ " + obj.getValor()));
			txtChave.setText(obj.getChave());
			labelError.setText("");
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Erro ao acessar banco de dados", null, e.getMessage(), AlertType.ERROR);
		} catch (NullPointerException e) {
			labelError.setText("Nenhuma nota encontrada!");
		}
	}
	
	public void onBtCopiarChaveAction() {
		Clipboard board = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
		board.setContents(new StringSelection(txtChave.getText()), null);
	}
	
	public void setSrqNfce(SrqNfce entity) {
		this.entity = entity;
	}
	
	public void setSrqNfceService(SrqNfceService service) {
		this.service = service;
	}
	
	private SrqNfce getFormData() {
		try {
			entity = new SrqNfce();
			service = new SrqNfceService();
	
			ValidationException exception = new ValidationException("Validation error");
	
			if (txtNumero.getText() == null || txtNumero.getText().trim().equals("")) {
				exception.addError("numero", "Número não pode ser vazio!");
			}
			else if (txtSerie.getText() == null || txtSerie.getText().trim().equals("")) {
				exception.addError("serie", "Série não pode ser vazio!");
			}
	
			
			if (exception.getErrors().size() > 0) {
				throw exception;
			}
			
	
			entity.setNumero(Utils.tryParseToInt(txtNumero.getText()));
			entity.setSerie(Utils.tryParseToInt(txtSerie.getText()));
			return entity;
		}
		catch (Exceptions e) {
			labelError.setText(e.getMessage());
			entity.setNumero(0);
			entity.setSerie(0);
			return entity;
		}
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

		if (fields.contains("numero")) {
			labelError.setText(errors.get("numero"));
			txtNumero.requestFocus();
		}
		
		if (fields.contains("serie")) {
			labelError.setText(errors.get("serie"));
			txtSerie.requestFocus();
		}
		
	}
}
