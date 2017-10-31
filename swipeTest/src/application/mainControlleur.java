package application;

import javafx.fxml.FXML;
import javafx.scene.input.SwipeEvent;
import javafx.scene.shape.Rectangle;

public class mainControlleur {

	@FXML
	Rectangle rect;
	
	@FXML
	public void initialize() {
		rect.setOnSwipeDown(this::sw);
		rect.setOnSwipeLeft(this::sw);
		rect.setOnSwipeRight(this::sw);
		rect.setOnSwipeUp(this::sw);
	}
	
	private void sw(SwipeEvent event)
	{
		System.out.println(event.getX()+"_"+event.getY());
	}
}
