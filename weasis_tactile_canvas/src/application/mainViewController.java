//proto de weasis tactile avec canvas
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;



public class mainViewController {

	private Scene scene;
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);
	
	@FXML
	ScrollPane peliculeViewer;
	@FXML
	VBox peliculeContener;
	@FXML
	HBox mainHBox;
	@FXML
	AnchorPane mainPane;
	@FXML
	SVGPath closePelicule;
	@FXML
	MainCanvas canvas;
	
	@FXML
	public void initialize() {
				
		canvas = new MainCanvas();
		mainHBox.getChildren().add(1,canvas);
		System.out.println("**INFO**  initialize mainView ");

		for (int i = 1; i <= 5; i++) {
			PeliculeCanvas c = new PeliculeCanvas((i%3)+1+".jpg");
			peliculeContener.getChildren().add(c);

			c.setOnDragDetected(this::handleOnDragDetected);
			c.setOnDragDone(this::handleOnDragDone);
		}//end for

		canvas.setOnDragOver(this::handleOnDragOver);
		canvas.setOnDragEntered(this::handleOnDragEntered);
		canvas.setOnDragExited(this::handleOnDragExited);
		canvas.setOnDragDropped(this::handleOnDragDropped);

		closePelicule.setOnTouchReleased(this::handleOnTouchRelease);
		closePelicule.setOnMouseClicked(this::handleOnMouseClicked);
		


        peliculeViewer.widthProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {


        		//Obtenir la taille en pixel de la scroll bar vertical
        		double sbWidth = 0;
				Set<Node> nodes = peliculeViewer.lookupAll(".scroll-bar");
		        for (final Node node : nodes) {
		            if (node instanceof ScrollBar) {
		                ScrollBar sb = (ScrollBar) node;
		                if (sb.getOrientation() == Orientation.VERTICAL && sb.isVisible() == true) {
		                    sbWidth = sb.getWidth();
		                }
		            }
		        }


        		for (Object iterable_element : peliculeContener.getChildren()) {

           			if(iterable_element.getClass() == PeliculeCanvas.class)
        			{
           				PeliculeCanvas pc = (PeliculeCanvas)iterable_element;
        				pc.setHeight((double)newVal - peliculeContener.getPadding().getLeft()
        						                    - peliculeContener.getPadding().getRight()
        						                    - sbWidth - 2);
        				pc.setWidth((double)newVal - peliculeContener.getPadding().getLeft()
        						                   - peliculeContener.getPadding().getRight()
        						                   - sbWidth - 2);//-2 permet de supprimer la scroll bar Horizontal
        				pc.Draw();
        			}
				}
        	}
		});
        
        pause.setOnFinished(this::pause);
        initHideShow();
	}

	public void setScrollBar(ScrollController sc) {
		canvas.setScrollBar(sc);
	}
	
	public void setParam(Scene scene) {
		this.scene = scene;
		
		peliculeViewer.prefWidthProperty().bind(scene.widthProperty().multiply(0.20));
		
		mainHBox.prefHeightProperty().bind(scene.heightProperty());
		mainHBox.prefWidthProperty().bind(scene.widthProperty());
    	
    	peliculeViewer.prefHeightProperty().bind(scene.heightProperty());
    	peliculeContener.prefHeightProperty().bind(scene.heightProperty());
    	
    	mainPane.prefHeightProperty().bind(scene.heightProperty());
    	mainPane.prefHeightProperty().bind(scene.heightProperty());
    	
    	canvas.widthProperty().bind(scene.widthProperty().subtract(peliculeViewer.widthProperty()));
    	canvas.heightProperty().bind(scene.heightProperty());
    	
    	
//        closePelicule.layoutXProperty().bind(canvas.layoutXProperty());
        canvas.layoutXProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
        		
        			closePelicule.setLayoutX((double) newVal);	
        	}
		});
	}

	/*****************************************************************
	 *                         DRAG                                  *
	 *****************************************************************/
	public void handleOnDragDetected(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture*/
		System.out.println("onDragDetected");
		if(!lockedProperty.getValue()) {
			/* allow any transfer mode */
			Dragboard db = ((PeliculeCanvas)event.getSource()).startDragAndDrop(TransferMode.COPY);
	
			SnapshotParameters sp = new SnapshotParameters();
			db.setDragView(((PeliculeCanvas)event.getSource()).snapshot(sp, null));
	
			/* put a string on dragboard */
			ClipboardContent content = new ClipboardContent();
			content.putString(((PeliculeCanvas)event.getSource()).imageName);
			db.setContent(content);
		}

		event.consume();
	}

	public void handleOnDragDone(DragEvent event) {
		/* the drag-and-drop gesture ended */
		System.out.println("onDragDone");
		/* if the data was successfully moved, clear it */
		if (event.getTransferMode() == TransferMode.COPY) {
			//vbox.getChildren().remove((Node)child);
				//im1.setImage(null);
			//((ImageView) child).setImage(event.getDragboard().getImage());
		}
		event.consume();
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
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvas.setOnScroll(this::handleOnScroll);
			canvas.setOnScrollStarted(this::handleOnScrollStart);
			canvas.setOnScrollFinished(this::handleOnScrollEnd);
			canvas.setOnZoom(this::handleOnZoom);
			canvas.setOnZoomStarted(this::handleOnZoomStarted);
			canvas.setOnZoomFinished(this::handleOnZoomFinished);
			canvas.setOnTouchPressed(this::handleTouchPress);
			canvas.setOnTouchReleased(this::handleTouchRelease);
			
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
				Translate(event.getDeltaX(), event.getDeltaY());
				break;
			case 1 :
				
				if (mode == 0 || mode == 1) {
					Contraste(event.getDeltaX(), event.getDeltaY());
				}
						
				break;
			}
		}
		event.consume();
	}

	public void handleOnScrollStart(ScrollEvent event) {
		event.consume();
		if(event.getTouchCount() > 2) {
			mode = 3;
		}
		event.consume();
	}

	public void handleOnScrollEnd(ScrollEvent event) {
		if(event.getTouchCount() == 0) {
			mode = 0;
		}
		event.consume();
	}


    /*****************************************************************
	 *                         ZOOM                                  *
	 *****************************************************************/

    public void handleOnZoom(ZoomEvent event) {
    	if(!lockedProperty.getValue()) {
    		if(mode == 2) {
    			Zoom(event.getZoomFactor(), event.getX(), event.getY());
    		}
    	}
        event.consume();
    }

    public void handleOnZoomStarted(ZoomEvent event) {
        event.consume();
    }

    public void handleOnZoomFinished(ZoomEvent event) {
       	if(!lockedProperty.getValue()) {
	        ZoomEnd();
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
			pause.stop();
			touchevent.add("press");
			pause.playFromStart();
		}
		event.consume();
	}
	private Double x,y;
	private void handleTouchRelease(TouchEvent event) {
		if(!lockedProperty.getValue()) {
			
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
    

    /*****************************************************************
	 *                         HIDE                                  *
	 *****************************************************************/
	private Boolean flagPelliculeShow = true;
	
	private final Duration HIDE_PELICULE_TIME = Duration.millis(250);
	ParallelTransition pt;
	
	Timeline timeline;
	KeyValue kv;
	KeyFrame kf;
	
	RotateTransition rotateTransition;
	
	private void initHideShow() {
		pt = new ParallelTransition();
		rotateTransition = new RotateTransition(HIDE_PELICULE_TIME, closePelicule);
    	timeline = new Timeline();
    	pt.getChildren().addAll(timeline,rotateTransition);
    	pt.setOnFinished(this::finishHidePellicule);
	}
	
    public void handleOnTouchRelease(TouchEvent event){
    	hidePellicule();
    	event.consume();
    }
    public void handleOnMouseClicked(MouseEvent event){
//    	hidePellicule();
    	event.consume();
    }


    private void hidePellicule() {
    	
    	if(!lockedProperty.getValue()) {
    		
	    	if(flagPelliculeShow) {
	        	peliculeViewer.prefWidthProperty().unbind();
	        	rotateTransition.setFromAngle(0);
	        	rotateTransition.setToAngle(180);
	        	kv = new KeyValue(peliculeViewer.prefWidthProperty(), 0);
	        	kf = new KeyFrame(HIDE_PELICULE_TIME, kv);
	        	timeline.getKeyFrames().clear();
	        	timeline.getKeyFrames().add(kf);
	        	pt.play();

	    	} else {
	        	rotateTransition.setFromAngle(180);
	        	rotateTransition.setToAngle(0);
	        	kv = new KeyValue(peliculeViewer.prefWidthProperty(), scene.getWidth()*0.2);
	        	kf = new KeyFrame(HIDE_PELICULE_TIME, kv);
	        	timeline.getKeyFrames().clear();
	        	timeline.getKeyFrames().add(kf);
				mainHBox.getChildren().add(0, peliculeViewer);	
				canvas.widthProperty().bind(scene.widthProperty().subtract(peliculeViewer.widthProperty()));
	        	pt.play();
	        	
	    	}
    	}
    }
    
	public void finishHidePellicule(ActionEvent event) {
		if(flagPelliculeShow) {
			mainHBox.getChildren().remove(peliculeViewer);
			canvas.widthProperty().bind(scene.widthProperty());
			flagPelliculeShow=!flagPelliculeShow;
		} else {
			peliculeViewer.prefWidthProperty().bind(scene.widthProperty().multiply(0.20));
			flagPelliculeShow=!flagPelliculeShow;
		}
		event.consume();
	}

    /*****************************************************************
	 *                     IMAGE MANIPULATION                        *
	 *****************************************************************/

    private void Zoom(double delta, double x, double y) {
    	canvas.zoom(delta, x , y);
    }
 
    public void ZoomEnd() {
    	canvas.zoomEnd();
    }
    
    private void Translate(double deltaX, double deltaY) {
    	canvas.translate(deltaX, deltaY);
    }

    private void Contraste(double x, double y) {
    	canvas.contraste(x, y);
    }

    public void setStrock(double s) {
    	canvas.scroll(s);
    }
    

    
    
}
