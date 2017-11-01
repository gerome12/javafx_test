package application;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class MenuController {

	@FXML
	Group menuGroup;

	@FXML
	BorderPane translate_1;
	@FXML
	BorderPane contraste_1;
	@FXML
	BorderPane translate_2;
	@FXML
	BorderPane contraste_2;
	@FXML
	GridPane menu;
	@FXML
	Node boutonPlus;
	@FXML
	Node exit;
	@FXML
	Node reset;

	
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

		translate_1.setStyle("-fx-background-color:GAINSBORO");
		translate_2.setStyle("-fx-background-color:GAINSBORO");

		//DRAG
		boutonPlus.setOnDragDetected(this::handleOnDragDetected);
		boutonPlus.setOnDragDone(this::handleOnDragDone);


		//DROP
		translate_1.setOnDragOver(this::handleOnDragOver);
		translate_1.setOnDragEntered(this::handleOnDragEntered);
		translate_1.setOnDragExited(this::handleOnDragExited);
		translate_1.setOnDragDropped(this::handleOnDragDropped);

		contraste_1.setOnDragOver(this::handleOnDragOver);
		contraste_1.setOnDragEntered(this::handleOnDragEntered);
		contraste_1.setOnDragExited(this::handleOnDragExited);
		contraste_1.setOnDragDropped(this::handleOnDragDropped);

		translate_2.setOnDragOver(this::handleOnDragOver);
		translate_2.setOnDragEntered(this::handleOnDragEntered);
		translate_2.setOnDragExited(this::handleOnDragExited);
		translate_2.setOnDragDropped(this::handleOnDragDropped);

		contraste_2.setOnDragOver(this::handleOnDragOver);
		contraste_2.setOnDragEntered(this::handleOnDragEntered);
		contraste_2.setOnDragExited(this::handleOnDragExited);
		contraste_2.setOnDragDropped(this::handleOnDragDropped);

		exit.setOnDragOver(this::handleOnDragOver);
		exit.setOnDragEntered(this::handleOnDragEntered);
		exit.setOnDragExited(this::handleOnDragExited);
		exit.setOnDragDropped(this::handleOnDragDroppedExit);
		
		reset.setOnDragOver(this::handleOnDragOver);
		reset.setOnDragEntered(this::handleOnDragEntered);
		reset.setOnDragExited(this::handleOnDragExited);
		reset.setOnDragDropped(this::handleOnDragDroppedReset);


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
        ToolBox.setTool(rect.getId());

        System.out.println(rect.getId());
        event.consume();
        ToolBox.outTool();
	}

	public void handleOnDragDroppedExit(DragEvent event) {

		Platform.exit();
		System.exit(0);
        event.setDropCompleted(true);
        event.consume();
	}
	
	public void handleOnDragDroppedReset(DragEvent event) {

		canvas.reset();
	}


	/*****************************************************************
	 *                   FadeTransition                              *
	 *****************************************************************/
	public void handleFadeTransitionEnd(ActionEvent event) {
		menu.setVisible(false);
		event.consume();
	}
}
