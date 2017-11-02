package application;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class MenuController {
	
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);

	@FXML
	Group menuGroup;
	@FXML
	GridPane menu;
	@FXML
	Node boutonPlus;
	@FXML
	Node exit;
	@FXML
	Node reset;
	@FXML
	Node lock;
	@FXML
	Node unlockBorder;
	@FXML
	ProgressIndicator unlockProgress;
	@FXML
	Node unlockProgressBorder;

	
	MainCanvas canvas;
	FadeTransition ftShow;
	FadeTransition ftHide;


	@FXML
	public void initialize() {
		System.out.println("**INFO**  initialize menu ");

		ftShow = new FadeTransition(Duration.millis(300), menu);
		ftShow.setFromValue(0);
		ftShow.setToValue(1);

		ftHide = new FadeTransition(Duration.millis(300), menu);
		ftHide.setFromValue(1);
		ftHide.setToValue(0);
		ftHide.setOnFinished(this::handleFadeTransitionEnd);

		menu.setVisible(false);

		//DRAG
		boutonPlus.setOnDragDetected(this::handleOnDragDetected);
		boutonPlus.setOnDragDone(this::handleOnDragDone);


		//DROP
		exit.setOnDragOver(this::handleOnDragOver);
		exit.setOnDragEntered(this::handleOnDragEntered);
		exit.setOnDragExited(this::handleOnDragExited);
		exit.setOnDragDropped(this::handleOnDragDroppedExit);
		
		reset.setOnDragOver(this::handleOnDragOver);
		reset.setOnDragEntered(this::handleOnDragEntered);
		reset.setOnDragExited(this::handleOnDragExited);
		reset.setOnDragDropped(this::handleOnDragDroppedReset);
		
		lock.setOnDragOver(this::handleOnDragOver);
		lock.setOnDragEntered(this::handleOnDragEntered);
		lock.setOnDragExited(this::handleOnDragExited);
		lock.setOnDragDropped(this::handleOnDragDroppedLock);

		
		initUnlock();
		unlockBorder.setOnTouchReleased(this::handleOnTouchReleaseUnlock);
		unlockBorder.setOnTouchPressed(this::handleOnTouchPressUnlock);
		
	}

	public void setParam(Scene scene, MainCanvas canvas){
		
		this.canvas = canvas;
		
		scene.widthProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
        		menuGroup.setLayoutX((double)newVal-(menuGroup.getBoundsInLocal().getWidth()-
        				(menuGroup.getBoundsInLocal().getWidth()-menuGroup.getBoundsInLocal().getMaxX()))-7);

        	}
		});
		scene.heightProperty().addListener(new ChangeListener<Object>() {
        	@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
        		menuGroup.setLayoutY((double)newVal-(menuGroup.getBoundsInLocal().getHeight()-
        				(menuGroup.getBoundsInLocal().getHeight()-menuGroup.getBoundsInLocal().getMaxY()))-7);

        	}
		});
	}
	

	/*****************************************************************
	 *                         DRAG                                  *
	 *****************************************************************/
	public void handleOnDragDetected(MouseEvent event) {
		if(!lockedProperty.get()) {
	        /* drag was detected, start drag-and-drop gesture*/
			Node bouton = (Node)event.getSource();
	        System.out.println(event.getEventType().getName());
	
	        /* allow any transfer mode */
	        Dragboard db = bouton.startDragAndDrop(TransferMode.LINK);
	
	        /* put a string on dragboard */
	        ClipboardContent content = new ClipboardContent();
	        content.putString("empty");
	        db.setContent(content);
	
	        menu.setVisible(true);
	        ftShow.play();
	        event.consume();
		}
	}

	public void handleOnDragDone(DragEvent event) {
    	System.out.println(event.getEventType().getName());
    	ftHide.play();

        event.consume();
	}


	/*****************************************************************
	 *                         DROP                                  *
	 *****************************************************************/

	public void handleOnDragOver(DragEvent event) {
		/* data is dragged over the target */
        System.out.println(event.getEventType().getName());
        event.acceptTransferModes(TransferMode.LINK);
        event.consume();
	}

	public void handleOnDragEntered(DragEvent event) {
        /* the drag-and-drop gesture entered the target */
		BorderPane rect = (BorderPane)event.getSource();
        System.out.println("onDragEntered");
        rect.setTranslateY(rect.getTranslateY()+2);
        event.consume();
	}

	public void handleOnDragExited(DragEvent event) {
        /* mouse moved away, remove the graphical cues */
		BorderPane rect = (BorderPane)event.getSource();
    	rect.setTranslateY(rect.getTranslateY()-2);
        event.consume();
	}

	public void handleOnDragDropped(DragEvent event) {
        /* data dropped */
		BorderPane rect = (BorderPane)event.getSource();
        System.out.println("onDragDropped");

        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(true);
        for (Node children : rect.getParent().getChildrenUnmodifiable()) {
        	BorderPane r = (BorderPane) children;
        	r.setStyle("-fx-background-color:none");
		}
        rect.setStyle("-fx-background-color:GAINSBORO");

        System.out.println(rect.getId());
        event.consume();
	}

	public void handleOnDragDroppedExit(DragEvent event) {

		Platform.exit();
		System.exit(0);
        event.setDropCompleted(true);
        event.consume();
	}
	
	public void handleOnDragDroppedReset(DragEvent event) {

		canvas.reset();
		event.consume();
	}
	
	public void handleOnDragDroppedLock(DragEvent event) {

		menuGroup.getChildren().add(unlockBorder);
		lockedProperty.set(true);
		event.consume();
		System.out.println("unlock is visible");
	}
	
	
	/*****************************************************************
	 *                   TouchEvent (lock)                           *
	 *****************************************************************/
	private final Duration UNLOCK_TIME = Duration.millis(800);
	
	final Timeline timeline = new Timeline();
	IntegerProperty progressProperty = new SimpleIntegerProperty();
	final KeyValue kv = new KeyValue(progressProperty, 100);
	final KeyFrame kf = new KeyFrame(UNLOCK_TIME, kv);
	
	private void initUnlock() {
		menuGroup.getChildren().remove(unlockBorder);
		timeline.getKeyFrames().add(kf);
		unlockProgress.progressProperty().bind(progressProperty.divide(100.0));
		unlockProgress.managedProperty().bind(unlockProgress.visibleProperty());
		unlockProgress.setVisible(false);
		
	}
	
	public void handleOnTouchPressUnlock(TouchEvent event) {
		if(lockedProperty.getValue()) {
			unlockProgress.setVisible(true);
			System.out.println("unlock starting");
			timeline.playFrom(Duration.ZERO);
		}
		
		event.consume();
	}
	
	public void handleOnTouchReleaseUnlock(TouchEvent event) {

		if(unlockProgress.getProgress() < 1) {
			
			timeline.stop();
		} else {
			System.out.println("unlock");
			lockedProperty.set(false);
			menuGroup.getChildren().remove(unlockBorder);
		}
		progressProperty.set(0);
		unlockProgress.setVisible(false);
		
		event.consume();
	}


	/*****************************************************************
	 *                   FadeTransition                              *
	 *****************************************************************/
	public void handleFadeTransitionEnd(ActionEvent event) {
		menu.setVisible(false);
		event.consume();
	}
}
