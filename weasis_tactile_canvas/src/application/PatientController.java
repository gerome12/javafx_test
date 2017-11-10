package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class PatientController {
	
	@FXML
	TitledPane patient;
	@FXML
	Group patientGroup;
	@FXML
	VBox patientImageVBox;
	
	String patientName;
	
	private Boolean accordeonMode = false;
		
	@FXML
	public void initialize() {
		patientImageVBox.prefWidthProperty().bind(patient.prefWidthProperty());
		
		patient.expandedProperty().addListener(this::accordeonListener);
	}
	
	public void setAccordeonMode(Boolean value) {
		accordeonMode = value;
	}
	
    private void accordeonListener(ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) {
    	if(accordeonMode == true)
    	{
	    	if(newVal == true)
			{
	    		VBox vb = (VBox)patientGroup.getParent();
	    		
	    		for (Object iterable_element : vb.getChildren()) {
	    			Group g = (Group)iterable_element;
	    			PatientController pc = (PatientController)g.getUserData();
	    			if(pc.patientName != patientName)
	    				pc.Close();
	//    			TitledPane tp = (TitledPane)g.getChildren().get(0);
	//    			if(tp.getId() != patient.getId())
	//    				tp.setExpanded(false);
	    		}
			}
    	}
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
	
	public void Open() {
		patient.setExpanded(true);
	}
	
	public void Close() {
		patient.setExpanded(false);
	}
	
	
}
