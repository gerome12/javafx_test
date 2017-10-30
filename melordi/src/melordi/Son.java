package melordi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;

public class Son extends Parent{
	
	public Slider slider;
	public Clavier clavier;
	
	public Son(Clavier clv) {
		
		clavier = clv;
		slider = new Slider(0,127, 63);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setTranslateY(35);
		
        ProgressIndicator indicateur = new ProgressIndicator(0.0);
        indicateur.progressProperty().bind(slider.valueProperty().divide(127.0));
        
        slider.valueProperty().addListener(new ChangeListener<Object>(){
            @Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal){
                clavier.requestFocus();
            }
        });

		
		this.getChildren().add(slider);
		this.setTranslateX(60);
		this.setTranslateY(260);
		
		
	}

}
