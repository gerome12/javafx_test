package application;

import java.util.Set;

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
import javafx.scene.image.ImageView;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;



public class mainViewController {


	@FXML
	ScrollPane peliculeViewer;
	@FXML
	VBox peliculeContener;
	@FXML
	SplitPane splitPane;
	@FXML
	AnchorPane destination;
	@FXML
	AnchorPane mainPane;
	@FXML
	SVGPath closePelicule;

//	Timeline timeLine;
//	Timeline returnArrow;
//	KeyValue kv;
//	KeyFrame kf;


	@FXML
	public void initialize() {
		System.out.println("**INFO**  initialize mainView ");

//		timeLine = new Timeline();
//		timeLine.setAutoReverse(true);
//		kv = new KeyValue(splitPane.getDividers().get(0).positionProperty(), 0);
//		kf = new KeyFrame(Duration.millis(2000), kv);
//		timeLine.getKeyFrames().add(kf);
//		timeLine.setOnFinished(this::handleFadeTransitionEnd);
//
//		returnArrow = new Timeline();
//		kv = new KeyValue(closePelicule.rotateProperty(), 180);
//		kf = new KeyFrame(Duration.millis(2000), kv);
//		returnArrow.getKeyFrames().add(kf);


		Color c =Color.WHEAT;
		for (int i = 1; i <= 5; i++) {
			Rectangle r = new Rectangle();
			r.setFill(c);
			c = c.darker();
			peliculeContener.getChildren().add(r);

			r.setOnDragDetected(this::handleOnDragDetected);
			r.setOnDragDone(this::handleOnDragDone);
		}//end for

		destination.setOnDragOver(this::handleOnDragOver);
		destination.setOnDragEntered(this::handleOnDragEntered);
		destination.setOnDragExited(this::handleOnDragExited);
		destination.setOnDragDropped(this::handleOnDragDropped);

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

           			if(iterable_element.getClass() == Rectangle.class)
        			{
        				Rectangle rect = (Rectangle)iterable_element;
        				rect.setHeight((double)newVal - peliculeContener.getPadding().getLeft()
        						                      - peliculeContener.getPadding().getRight()
        						                      - sbWidth);
        				rect.setWidth((double)newVal - peliculeContener.getPadding().getLeft()
        						                     - peliculeContener.getPadding().getRight()
        						                     - sbWidth);
        			}
           			else if(iterable_element.getClass() == ImageView.class)
        			{
           				((ImageView)iterable_element).setFitWidth((double)newVal - peliculeContener.getPadding().getLeft()
           						                                                 - peliculeContener.getPadding().getRight()
           						                                                 - sbWidth);
        			}
				}
        	}
		});
	}

	public void setParam(Scene scene) {
		splitPane.minHeightProperty().bind(scene.heightProperty());
		splitPane.minWidthProperty().bind(scene.widthProperty());
    	splitPane.setDividerPosition(0, 0.1);
	}

	/*****************************************************************
	 *                         DRAG                                  *
	 *****************************************************************/
	public void handleOnDragDetected(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture*/
		System.out.println("onDragDetected");

		/* allow any transfer mode */
		Dragboard db = ((Rectangle)event.getSource()).startDragAndDrop(TransferMode.COPY);

		SnapshotParameters sp = new SnapshotParameters();
		db.setDragView(((Rectangle)event.getSource()).snapshot(sp, null));

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(event.getSource().toString());
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
			destination.getChildren().clear();
			Rectangle r = new Rectangle(200,200);
			r.setOnScroll(this::handleOnScroll);
			r.setOnScrollStarted(this::handleOnScrollStart);
			r.setOnScrollFinished(this::handleOnScrollEnd);
			r.setOnZoom(this::handleOnZoom);
			r.setOnZoomStarted(this::handleOnZoomStarted);
			r.setOnZoomFinished(this::handleOnZoomFinished);
			r.setFill(Color.web(db.getString().split("=")[5].replace("]", "").replace("0x", "#")));
			r.setOnTouchPressed(this::toto);
			destination.getChildren().add(r);
		}

		event.setDropCompleted(true);

		event.consume();
	}

	/*****************************************************************
	 *                         SCROLL                                *
	 *****************************************************************/

	public void handleOnScroll(ScrollEvent event) {
		Rectangle rect = (Rectangle) event.getSource();

		switch (event.getTouchCount()) {
		case 2 :
			switch (ToolBox.getToolTwoFinger()) {
			case ToolBox.TRANSLATE:
				Translate(rect, event.getDeltaX(), event.getDeltaY(), 2);
				break;
			case ToolBox.CONTRASTE:
				Contraste(rect, event.getDeltaX(), event.getDeltaY(), 2);
				break;
			}
			break;
		case 1 :
			switch (ToolBox.getToolOneFinger()) {
			case ToolBox.TRANSLATE:
				Translate(rect, event.getDeltaX(), event.getDeltaY(), 1);
				break;
			case ToolBox.CONTRASTE:
				Contraste(rect, event.getDeltaX(), event.getDeltaY(), 1);
				break;
			}
			break;
		}
			System.out.println(event.getEventType().getName()+
							", inertia: " + event.isInertia() +
							", direct: " + event.isDirect());

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
        Zoom((Rectangle) event.getSource(), event.getZoomFactor(), event.getZoomFactor());
        System.out.println(event.getEventType().getName()+
                ", inertia: " + event.isInertia() +
                ", direct: " + event.isDirect());
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

    }

	//*************** FadeTransition
	public void handleFadeTransitionEnd(ActionEvent event) {

		event.consume();
	}


    /*****************************************************************
	 *                     IMAGE MANIPULATION                        *
	 *****************************************************************/

    private void Zoom(Rectangle rect, double deltaX, double deltaY) {
        rect.setScaleX(rect.getScaleX() * deltaX);
        rect.setScaleY(rect.getScaleY() * deltaY);
    }

    private void Translate(Rectangle rect, double deltaX, double deltaY, int NbFinger) {
		rect.setTranslateX(rect.getTranslateX() + deltaX);
		rect.setTranslateY(rect.getTranslateY() + deltaY);
    }

    private void Contraste(Rectangle rect, double x, double y, int NbFinger) {
		Color c = (Color)rect.getFill();

		if(Math.abs(x) >= 4 && Math.abs(x) > Math.abs(y)) {
			c = c.darker();
		}
		else if(Math.abs(y) >= 4 && Math.abs(x) < Math.abs(y)) {
			c = c.brighter();
		}
		rect.setFill(c);
    }

    private void toto(TouchEvent e){
    	System.out.println("Touch");

    	e.consume();
    }

    public void setStrock(double s) {
    	System.out.println("xxxx");
    	for (Node children : destination.getChildren()) {
			if(children.getClass() == Rectangle.class) {
				Rectangle rect = (Rectangle)children;
				System.out.println(rect);
				rect.setStroke(Color.BLACK);
				rect.setStrokeWidth(s);
			}
		}
    }

}
