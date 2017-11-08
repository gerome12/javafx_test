package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.util.Duration;

public class MainCanvasController {
	
	private MainCanvas canvas;
	
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);
	
	public MainCanvasController(MainCanvas canvas) {
		this.canvas = canvas;
		
		this.canvas.setOnDragOver(this::handleOnDragOver);
		this.canvas.setOnDragEntered(this::handleOnDragEntered);
		this.canvas.setOnDragExited(this::handleOnDragExited);
		this.canvas.setOnDragDropped(this::handleOnDragDropped);
		
		this.canvas.setOnScroll(this::handleOnScroll);
		this.canvas.setOnScrollStarted(this::handleOnScrollStart);
		this.canvas.setOnScrollFinished(this::handleOnScrollEnd);
		this.canvas.setOnZoom(this::handleOnZoom);
		this.canvas.setOnZoomStarted(this::handleOnZoomStarted);
		this.canvas.setOnZoomFinished(this::handleOnZoomFinished);
		this.canvas.setOnTouchPressed(this::handleTouchPress);
		this.canvas.setOnTouchReleased(this::handleTouchRelease);
		
		pause.setOnFinished(this::pause);
	}
	

	
	/*****************************************************************
	 *                         DROP                                  *
	 *****************************************************************/

	public void handleOnDragOver(DragEvent event) {
		/* data is dragged over the target */
		event.acceptTransferModes(TransferMode.COPY);
		event.consume();
	}

	public void handleOnDragEntered(DragEvent event) {
		/* the drag-and-drop gesture entered the target */
		System.out.println("onDragEntered");
		event.consume();
	}

	public void handleOnDragExited(DragEvent event) {
		/* mouse moved away, remove the graphical cues */
		event.consume();
	}

	public void handleOnDragDropped(DragEvent event) {
		/* data dropped */
		System.out.println("onDragDropped");

		/* let the source know whether the string was successfully
		 * transferred and used */
		Dragboard db = event.getDragboard();
		if (db.hasString()) {			
			canvas.setImage(db.getString());
			canvas.reset();
			canvas.draw();
		}

		event.setDropCompleted(true);

		event.consume();
	}
	
	/*****************************************************************
	 *                         SCROLL                                *
	 *****************************************************************/
	private Integer mode = 0;
	
	public void handleOnScroll(ScrollEvent event) {
		if(!lockedProperty.getValue()) {
	
			switch (event.getTouchCount()) {
			case 2 :
				mode = 2;
				canvas.translate(event.getDeltaX(), event.getDeltaY());
				break;
			case 1 :
				
				if (mode == 0 || mode == 1) {
					mode = 1;
					canvas.contraste(event.getDeltaX(), event.getDeltaY());
				}		
				break;
			}
		}
		event.consume();
	}

	public void handleOnScrollStart(ScrollEvent event) {
		event.consume();
	}

	public void handleOnScrollEnd(ScrollEvent event) {
		event.consume();
	}


    /*****************************************************************
	 *                         ZOOM                                  *
	 *****************************************************************/

    public void handleOnZoom(ZoomEvent event) {
    	if(!lockedProperty.getValue()) {
    		if(mode == 2) {
    			canvas.zoom(event.getZoomFactor(), event.getX(), event.getY());
    		}
    	}
        event.consume();
    }

    public void handleOnZoomStarted(ZoomEvent event) {
        event.consume();
    }

    public void handleOnZoomFinished(ZoomEvent event) {
       	if(!lockedProperty.getValue()) {
	        canvas.zoomEnd();
    	}
    	event.consume();
    }
    
    
    /*****************************************************************
	 *                         TOUCH                                  *
	 *****************************************************************/
    
    private static ArrayList<String> doubleTab2Fingers = new ArrayList<String>(Arrays.asList("press","press","release","release","press","press","release","release"));
    private static ArrayList<String> doubleTab3Fingers = new ArrayList<String>(Arrays.asList("press","press","press","release","release","release","press","press","press","release","release","release"));
    private static ArrayList<String> doubleTab1Finger = new ArrayList<String>(Arrays.asList("press","release","press","release"));
    
    private ArrayList<String> touchevent = new ArrayList<>();
    
    PauseTransition pause = new PauseTransition(Duration.millis(130));
    
    
	private void handleTouchPress(TouchEvent event) {
		if(!lockedProperty.getValue()) {
			
			if(event.getTouchCount() > 2) {
				mode = 3;
			}
			
			pause.stop();
			touchevent.add("press");
			pause.playFromStart();
		}
		event.consume();
	}
	private Double x,y;
	private void handleTouchRelease(TouchEvent event) {
		if(!lockedProperty.getValue()) {
			
			if(event.getTouchCount() == 1) {
				mode = 0;
			}
			
			x = event.getTouchPoint().getX();
			y = event.getTouchPoint().getY();
			
			pause.stop();
			touchevent.add("release");
			pause.playFromStart();
		}
		event.consume();
	}
	
	private void pause(ActionEvent e) {
		if(touchevent.equals(doubleTab2Fingers)) {
			System.out.println("doubletap2fingers");
			canvas.zoom(0.5, x, y);
			canvas.zoomEnd();
		}else if (touchevent.equals(doubleTab1Finger)) {
			System.out.println("doubletap1finger");
			canvas.zoom(2, x, y);	
			canvas.zoomEnd();
		}
		else if (touchevent.equals(doubleTab3Fingers)) {
			System.out.println("doubleTab3Fingers");
			canvas.reset();
		}
		touchevent.clear();
	}

}
