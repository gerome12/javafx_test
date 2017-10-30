package application;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
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
	private FadeTransition ftShow;
	private PauseTransition pause = new PauseTransition(Duration.seconds(1));

	private mainViewController vc;

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
            	vc.setStrock((double)new_val);
                System.out.println("scrollBar : "+(int)scrollBar.getValue());
            }
        });


		ftHide = new FadeTransition(Duration.millis(1000), scrollBar);
		ftHide.setFromValue(0.6);
		ftHide.setToValue(0);
		ftHide.setOnFinished(this::handleFadeTransitionEnd);

		ftShow = new FadeTransition(Duration.millis(200), scrollBar);
		ftShow.setFromValue(0);
		ftShow.setToValue(0.6);

		pause.setOnFinished(e->ftHide.playFromStart());

		scrollBar.setVisible(false);
	}


	public void setParam(Scene scene, mainViewController vc){

		this.vc = vc;

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
	private void scrollStart(ScrollEvent event) {
		event.consume();
	}

	private void scroll(ScrollEvent event) {
		if(!event.isInertia()){
			Double delta = event.getDeltaY();
			Double val = scrollBar.getValue() + ((delta * scrollBar.getMax())/ scrollBar.getHeight());

			if(val > scrollBar.getMax())
				val = scrollBar.getMax();
			else if (val < scrollBar.getMin())
				val = scrollBar.getMin();

			scrollBar.setValue(val);
			pause.stop();
			event.consume();
		}
	}

	private void scrollFinish(ScrollEvent event) {
		pause.playFromStart();
	}

	/*****************************************************************
	 *                         TOUCH                                  *
	 *****************************************************************/
	private double startTime;
	private double timeFirstTouch;
	private void touchPress(TouchEvent event) {
		startTime = System.currentTimeMillis();
		ftHide.stop();
		if(!scrollBar.isVisible())
			ftShow.playFromStart();
		else {
			ftShow.jumpTo(Duration.millis(200));
			ftShow.play();
		}
		scrollBar.setVisible(true);
		event.consume();

	}

	private void touchRelease(TouchEvent event) {
		if(System.currentTimeMillis() - startTime < 300){
			pause.playFromStart();
			if(System.currentTimeMillis() - timeFirstTouch < 300) {
				System.out.println("double");
				scrollBar.setValue((event.getTouchPoint().getY()*scrollBar.getMax()) / zoneScrollVirtual_.getHeight());
			}
			else
			{
				System.out.println("simple");
				timeFirstTouch = System.currentTimeMillis();
				Double posY = event.getTouchPoint().getY();
				Double posScroll = (scrollBar.getValue() * zoneScrollVirtual_.getHeight()) / scrollBar.getMax();
				if(posY < posScroll)
					scrollBar.setValue(scrollBar.getValue()-1);
				else
					scrollBar.setValue(scrollBar.getValue()+1);

			}
		}
		event.consume();
	}

	/*****************************************************************
	 *                   FadeTransition                              *
	 *****************************************************************/
	public void handleFadeTransitionEnd(ActionEvent event) {
		scrollBar.setVisible(false);
		event.consume();
	}
}
