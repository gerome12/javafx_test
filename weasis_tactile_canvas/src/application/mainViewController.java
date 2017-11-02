//proto de weasis tactile avec canvas
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javafx.animation.PauseTransition;
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
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;



public class mainViewController {


	@FXML
	ScrollPane peliculeViewer;
	@FXML
	VBox peliculeContener;
	@FXML
	SplitPane splitPane;
//	@FXML
//	AnchorPane destination;
	@FXML
	AnchorPane mainPane;
	@FXML
	SVGPath closePelicule;
	@FXML
	MainCanvas canvas;
	
	private Scene scene;
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);




	@FXML
	public void initialize() {
		
		canvas = new MainCanvas();
		splitPane.getItems().add(1,canvas);
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



		peliculeViewer.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25));
		peliculeViewer.minWidthProperty().bind(splitPane.widthProperty().multiply(0.10));
//		closePelicule.layoutXProperty().bind(peliculeViewer.widthProperty()); // fonctionne pas =(




        peliculeViewer.widthProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {


        		closePelicule.setLayoutX((double)newVal);

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
        						                    - sbWidth);
        				pc.setWidth((double)newVal - peliculeContener.getPadding().getLeft()
        						                   - peliculeContener.getPadding().getRight()
        						                   - sbWidth);
        				pc.Draw();
        			}
				}
        	}
		});
        
        pause.setOnFinished(this::pause);
	}

	public void setScrollBar(ScrollController sc) {
		canvas.setScrollBar(sc);
	}
	
	public void setParam(Scene scene) {
		this.scene = scene;
		
		splitPane.prefHeightProperty().bind(scene.heightProperty());
		splitPane.prefWidthProperty().bind(scene.widthProperty());
    	splitPane.setDividerPosition(0, 0.1);
    	
    	peliculeViewer.prefHeightProperty().bind(scene.heightProperty());
    	peliculeContener.prefHeightProperty().bind(scene.heightProperty());
    	
    	mainPane.prefHeightProperty().bind(scene.heightProperty());
    	mainPane.prefHeightProperty().bind(scene.heightProperty());
    	
//    	destination.prefHeightProperty().bind(scene.heightProperty());

		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	canvas.setHeight(scene.getHeight());
		    	resizeCanvas();
//		    	destination.setLayoutX(200);
//		    	destination.setPrefWidth(value);
		    }
		});
		
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	resizeCanvas();
		    }
		});
    	
	}

	/*****************************************************************
	 *                         DRAG                                  *
	 *****************************************************************/
	public void handleOnDragDetected(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture*/
		System.out.println("onDragDetected");

		/* allow any transfer mode */
		Dragboard db = ((PeliculeCanvas)event.getSource()).startDragAndDrop(TransferMode.COPY);

		SnapshotParameters sp = new SnapshotParameters();
		db.setDragView(((PeliculeCanvas)event.getSource()).snapshot(sp, null));

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(((PeliculeCanvas)event.getSource()).imageName);
		db.setContent(content);

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
		System.out.println(event.getEventType().getName());
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

	public void handleOnScroll(ScrollEvent event) {
		if(!lockedProperty.getValue()) {
			MainCanvas rect = (MainCanvas) event.getSource();
	
			switch (event.getTouchCount()) {
			case 2 :
				Translate(rect, event.getDeltaX(), event.getDeltaY(), 2);
				break;
			case 1 :
				Contraste(rect, event.getDeltaX(), event.getDeltaY(), 1);
				break;
			}
				System.out.println(event.getEventType().getName()+", inertia: " 
								 + event.isInertia() +", direct: " + event.isDirect());
		}
			event.consume();
	}

	public void handleOnScrollStart(ScrollEvent event) {
		System.out.println(event.getEventType().getName());
		event.consume();
	}

	public void handleOnScrollEnd(ScrollEvent event) {
		System.out.println(event.getEventType().getName());
		event.consume();
	}


    /*****************************************************************
	 *                         ZOOM                                  *
	 *****************************************************************/

    public void handleOnZoom(ZoomEvent event) {
    	if(!lockedProperty.getValue()) {
	        Zoom((MainCanvas) event.getSource(), event.getZoomFactor(), event.getZoomFactor());
	        System.out.println(event.getEventType().getName()+
	                ", inertia: " + event.isInertia() +
	                ", direct: " + event.isDirect());
    	}
        event.consume();
    }

    public void handleOnZoomStarted(ZoomEvent event) {
        System.out.println(event.getEventType().getName());
        event.consume();
    }

    public void handleOnZoomFinished(ZoomEvent event) {
        System.out.println(event.getEventType().getName());
        event.consume();
    }
    
    
    /*****************************************************************
	 *                         TOUCH                                  *
	 *****************************************************************/
    
    
//	private double startTime;
//	private double timeFirstTouch;
//	private double[] tabTimeTouch;
//
//	private void handleTouchPress(TouchEvent event) {
//	startTime = System.currentTimeMillis();
//	
//
//	event.consume();
//
//}
//
//private void handleTouchRelease(TouchEvent event) {
//	if(System.currentTimeMillis() - startTime < 300){
//
//		if(System.currentTimeMillis() - timeFirstTouch < 300) {
//			System.out.println("double");
//			switch(event.getTouchCount())
//			{
//			case 1 : 
//				canvas.zoom(2, 2);
//				break;
//			case 2 : 
//				canvas.zoom(0.5, 0.5);
//				break;
//			}
//		}
//		else
//		{
//			System.out.println("simple");
//			timeFirstTouch = System.currentTimeMillis();
//		}
//	}
//	event.consume();
//}
    
    private static ArrayList<String> doubleTab2Fingers = new ArrayList<String>(Arrays.asList("press","press","release","release","press","press","release","release"));
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
	
	private void handleTouchRelease(TouchEvent event) {
		if(!lockedProperty.getValue()) {
			pause.stop();
			touchevent.add("release");
			pause.playFromStart();
		}
		event.consume();
	}
	
	private void pause(ActionEvent e) {
		if(touchevent.equals(doubleTab2Fingers)) {
			System.out.println("doubletap2fingers");
			canvas.zoom(0.5, 0.5);
		}else if (touchevent.equals(doubleTab1Finger)) {
			System.out.println("doubletap1finger");
			canvas.zoom(2, 2);			
		}
		System.out.println("pause");
		touchevent.clear();
	}
    

    /*****************************************************************
	 *                         HIDE                                  *
	 *****************************************************************/
    public void handleOnTouchRelease(TouchEvent event){
    	hidePellicule();
    }
    public void handleOnMouseClicked(MouseEvent event){
    	hidePellicule();
    }

    private Double dividerPositions;
    private Double closePeliculePosition;
    private void hidePellicule() {
    	
    	if(!lockedProperty.getValue()) {
	    	
	    	if(peliculeViewer.isVisible()) {
	    		closePelicule.setRotate(180);
	    		closePeliculePosition = closePelicule.getLayoutX();
	    		dividerPositions = splitPane.getDividerPositions()[0];
	    		splitPane.getItems().remove(peliculeViewer);
	    		closePelicule.setLayoutX(0);
	    		peliculeViewer.setVisible(false);
	    	} else{
	    		closePelicule.setLayoutX(closePeliculePosition);
	    		closePelicule.setRotate(0);
	    		splitPane.getItems().add(0, peliculeViewer);
	    		splitPane.setDividerPosition(0, dividerPositions);
	    		peliculeViewer.setVisible(true);
	    	}
	    	
	    	resizeCanvas();
    	}
    }

	//*************** FadeTransition
	public void handleFadeTransitionEnd(ActionEvent event) {

		event.consume();
	}


    /*****************************************************************
	 *                     IMAGE MANIPULATION                        *
	 *****************************************************************/

    private void Zoom(MainCanvas c, double deltaX, double deltaY) {
        c.zoom(deltaX,  deltaY);
    }

    private void Translate(MainCanvas c, double deltaX, double deltaY, int NbFinger) {
    	c.translate(deltaX, deltaY);
    }

    private void Contraste(MainCanvas c, double x, double y, int NbFinger) {
    	c.contraste(x, y);
    }

    public void setStrock(double s) {
    	canvas.scroll(s);
    }
    
    
    
    
    public void resizeCanvas() {
    	int l  = splitPane.getDividerPositions().length;
    	if(l==0) {
    		canvas.setWidth(scene.getWidth());
    	} else if(l==1) {
    		canvas.setWidth(scene.getWidth()*(1-splitPane.getDividerPositions()[0]));
    	}
    	
//    	GraphicsContext gc = canvas.getGraphicsContext2D();
//    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        gc.setFill(Color.YELLOW);
//        gc.setStroke(Color.YELLOW);
//        gc.fillOval(0, 0, 20, 20);
//        gc.fillOval(canvas.getWidth()-20, 0, 20, 20);
//        gc.fillOval(0, canvas.getHeight()-20, 20, 20);
//        gc.fillOval(canvas.getWidth()-20, canvas.getHeight()-20, 20, 20);
    }

}
