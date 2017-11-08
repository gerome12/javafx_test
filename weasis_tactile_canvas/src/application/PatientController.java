package application;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class PatientController {
	
	@FXML
	TitledPane patient;
	@FXML
	VBox patientImageVBox;
	
	private String patientName;
	
	@FXML
	public void initialize() {
		patientImageVBox.prefWidthProperty().bind(patient.prefWidthProperty());
	}
	
	public void addPeliculeCanvas(PeliculeCanvas peliculeCanvas) {
		patientImageVBox.getChildren().add(peliculeCanvas);
	}
	
	public void setName(String patientName) {
		patient.setText(patientName);
		this.patientName = patientName;
	}
	
	public void SetExpanded(Boolean value) {
		patient.setExpanded(value);
	}
	
}
