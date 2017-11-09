package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Rectangle;


public class mainControlleur {

	@FXML
	Rectangle rect;
	
	@FXML
	JFXListView<Label> lst;
	
	@FXML
	public void initialize() {
		rect.setOnSwipeDown(this::sw);
		rect.setOnSwipeLeft(this::sw);
		rect.setOnSwipeRight(this::sw);
		rect.setOnSwipeUp(this::sw);
		rect.setOnTouchPressed(this::touchP);
		rect.setOnTouchReleased(this::touchR);
		
		
		
		for(int i = 0 ; i < 100 ; i++) lst.getItems().add(new Label("Item " + i));
		lst.getStyleClass().add("mylistview");
		lst.setVerticalGap(1.0);

	}
	
	private void sw(SwipeEvent event)
	{
		System.out.println("Swipe position : "+event.getX()+" "+event.getY());
	}
	
	private double x,y;
	private void touchP(TouchEvent event)
	{
		x=event.getTouchPoint().getX();
		y=event.getTouchPoint().getY();
	}
	
	private void touchR(TouchEvent event)
	{
		
		x=(x+event.getTouchPoint().getX())/2;
		y=(y+event.getTouchPoint().getY())/2;
		System.out.println("Touch position (moyenne): "+x+" "+y);
		System.out.println("Touch position (x2): "+2*x+" "+2*y);
	}
}
