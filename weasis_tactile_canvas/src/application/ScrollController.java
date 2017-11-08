package application;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class ScrollController {

	@FXML
	private Group zoneScrollVirtual;
	@FXML
	private ScrollBar scrollBar;
	@FXML
	private Rectangle zoneScrollVirtual_;

	private FadeTransition ftHide;
	private PauseTransition pause = new PauseTransition(Duration.seconds(1));
	
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);

	private MainCanvasController mainCanvasController;

	@FXML
	public void initialize() {
		scrollBar.prefHeightProperty().bind(zoneScrollVirtual_.heightProperty());
		scrollBar.layoutYProperty().bind(zoneScrollVirtual_.layoutYProperty());
		zoneScrollVirtual_.setOnScroll(this::scroll);
		zoneScrollVirtual_.setOnScrollStarted(this::scrollStart);
		zoneScrollVirtual_.setOnScrollFinished(this::scrollFinish);
		zoneScrollVirtual_.setOnTouchPressed(this::touchPress);
		zoneScrollVirtual_.setOnTouchReleased(this::touchRelease);

        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
            	mainCanvasController.setScroll((double)new_val);
				ftHide.stop();	
            }
        });


		ftHide = new FadeTransition(Duration.millis(1000), scrollBar);
		ftHide.setFromValue(0.6);
		ftHide.setToValue(0);
		ftHide.setOnFinished(this::handleFadeTransitionEnd);

		pause.setOnFinished(e->ftHide.playFromStart());

		ftHide.playFrom(Duration.millis(1000));
	}
	
	public void setParam(Scene scene, MainCanvasController mainCanvasController){

		this.mainCanvasController = mainCanvasController;

		scrollBar.layoutXProperty().bind(scene.widthProperty().subtract(50));

		scene.widthProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
        		zoneScrollVirtual_.setLayoutX((double)newVal-(zoneScrollVirtual_.getBoundsInLocal().getWidth()-
        				(zoneScrollVirtual_.getBoundsInLocal().getWidth()-zoneScrollVirtual_.getBoundsInLocal().getMaxX())));

        	}
		});
		scene.heightProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
        		zoneScrollVirtual_.setHeight((Double)newVal-100);

        	}
		});
	}


	/*****************************************************************
	 *                         SCROLL                                *
	 *****************************************************************/
	public void setValue(int val) {
		scrollBar.setValue(val);
	}
	
	private void scrollStart(ScrollEvent event) {
		event.consume();
	}

	private void scroll(ScrollEvent event) {
		if(!lockedProperty.getValue()) {
			if(!event.isInertia()){
				scrollBar.setOpacity(0.6);
				Double delta = event.getDeltaY();
				Double val = scrollBar.getValue() + ((delta * scrollBar.getMax())/ scrollBar.getHeight());
	
				if(val > scrollBar.getMax())
					val = scrollBar.getMax();
				else if (val < scrollBar.getMin())
					val = scrollBar.getMin();
	
				scrollBar.setValue(val);
				pause.stop();
			}
		}
		event.consume();
	}

	private void scrollFinish(ScrollEvent event) {
		pause.playFromStart();
	}

	/*****************************************************************
	 *                         TOUCH                                  *
	 *****************************************************************/
	private double startTime;
	private double timeFirstTouch;
	private Integer TouchID;
	private void touchPress(TouchEvent event) {
		if(event.getTouchCount() == 1) {
			if(!lockedProperty.getValue()) {
				scrollBar.setOpacity(0.6);
				TouchID = event.getTouchPoint().getId();
				startTime = System.currentTimeMillis();		
			}
		}
		event.consume();
	}

	private void touchRelease(TouchEvent event) {
		if(TouchID == event.getTouchPoint().getId()) {
			pause.playFromStart();
			if(!lockedProperty.getValue()) {
				if(System.currentTimeMillis() - startTime < 300){
					
					if(System.currentTimeMillis() - timeFirstTouch < 300) {
						System.out.println("scrollBar double");

						scrollBar.setValue((event.getTouchPoint().getY()*scrollBar.getMax()) / zoneScrollVirtual_.getHeight());
					}
					else
					{
						Double val;
						System.out.println("scrollBar simple");
						timeFirstTouch = System.currentTimeMillis();
			
						if(event.getTouchPoint().getY() < zoneScrollVirtual_.getHeight() / 2)
							val = scrollBar.getValue()-1;
						else
							val = scrollBar.getValue()+1;
						
						if(val > scrollBar.getMax())
							val = scrollBar.getMax();
						else if (val < scrollBar.getMin())
							val = scrollBar.getMin();
						
						scrollBar.setValue(val);
					}
				}
			}
		}
		event.consume();
	}

	/*****************************************************************
	 *                   FadeTransition                              *
	 *****************************************************************/
	public void handleFadeTransitionEnd(ActionEvent event) {
		event.consume();
	}
}
