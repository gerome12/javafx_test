package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Transform;

public class vuePrincipaleController {



	@FXML private BorderPane border;

	@FXML private Text txtInfo;
	@FXML private Rectangle rect;
	@FXML private AnchorPane menu;

	@FXML private Rectangle rect2;
	@FXML private AnchorPane menu2;
	@FXML private Rectangle b4;
	@FXML private Rectangle b5;

	private boolean stateMenu = true;
	private Rectangle selectedB;


	@FXML
    public void initialize() {
		selectedB = b4;
		selectedB.setFill(Color.GAINSBORO);

		/************************************************************
		 *                        MENU 1                            *
		 ************************************************************/

		rect.setOnTouchPressed(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	//ouverture du menu
            	txtInfo.setText(event.getEventType().getName());
                event.consume();

	            menu.setVisible(! stateMenu);
            	stateMenu = ! stateMenu;
                rect.setFill(Color.BEIGE);
            }
        });

        rect.setOnTouchReleased(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	//fremeture du menu
            	txtInfo.setText(event.getEventType().getName());
                event.consume();
                rect.setFill(Color.DODGERBLUE);
            }
        });


        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               	//ouverture du menu
            	txtInfo.setText(event.getEventType().getName());
                event.consume();

	            menu.setVisible(! stateMenu);
            	stateMenu = ! stateMenu;

            	if(stateMenu)
            		rect.setFill(Color.BEIGE);
            	else
            		rect.setFill(Color.DODGERBLUE);
            }
        });


    	/************************************************************
    	 *                        MENU 2                            *
    	 ************************************************************/

        rect2.setOnDragDetected(new EventHandler <MouseEvent>() {
	        public void handle(MouseEvent event) {
	            /* drag was detected, start drag-and-drop gesture*/
	            System.out.println(event.getEventType().getName());

	            /* allow any transfer mode */
	            Dragboard db = rect2.startDragAndDrop(TransferMode.LINK);
	            SnapshotParameters sp = new SnapshotParameters();
	            sp.setTransform(Transform.scale(2,1));
	            db.setDragView(rect.snapshot(sp, null));
	            /* put a string on dragboard */
	            ClipboardContent content = new ClipboardContent();
	            content.putString("kk");
	            db.setContent(content);

	            //menu2.setCursor(Cursor.WAIT);
	            //event.
	            menu2.setVisible(true);
	            event.consume();
	        }
	    });

		rect2.setOnDragDone(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	        	System.out.println(event.getEventType().getName());
	        	if(event.getTransferMode() == null)
	        		txtInfo.setText("aucun outil sélectionné");
	        	menu2.setVisible(false);

	            event.consume();
	        }
	    });




		b4.setOnDragOver(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data is dragged over the target */
	            System.out.println(event.getEventType().getName());
	            event.acceptTransferModes(TransferMode.LINK);

	            ((Node)event.getGestureSource()).setCursor(Cursor.HAND);

	            event.consume();
	        }
	    });

		b4.setOnDragEntered(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* the drag-and-drop gesture entered the target */
	            System.out.println("onDragEntered");
	            b4.setTranslateY(b4.getTranslateY()+2);
	            event.consume();
	        }
	    });

		b4.setOnDragExited(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* mouse moved away, remove the graphical cues */
	        	b4.setTranslateY(b4.getTranslateY()-2);
	            event.consume();
	        }
	    });

		b4.setOnDragDropped(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data dropped */
	            System.out.println("onDragDropped");

	            /* let the source know whether the string was successfully
	             * transferred and used */
	            txtInfo.setText("BOUTON_4");
	            event.setDropCompleted(true);
	            selectedB = b4;
	            selectedB.setFill(Color.GAINSBORO);
	            b5.setFill(Color.BLACK);
	            System.out.println(selectedB.getId());
	            event.consume();
	        }
	    });

		///////////////////////////
		b5.setOnDragOver(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data is dragged over the target */
	            System.out.println(event.getEventType().getName());
	            event.acceptTransferModes(TransferMode.LINK);

	            ((Node)event.getGestureSource()).setCursor(Cursor.HAND);

	            event.consume();
	        }
	    });

		b5.setOnDragEntered(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* the drag-and-drop gesture entered the target */
	            System.out.println("onDragEntered");
	            b5.setTranslateY(b5.getTranslateY()+2);
	            event.consume();
	        }
	    });

		b5.setOnDragExited(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* mouse moved away, remove the graphical cues */
	        	b5.setTranslateY(b5.getTranslateY()-2);
	            event.consume();
	        }
	    });

		b5.setOnDragDropped(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data dropped */
	            System.out.println("onDragDropped");

	            /* let the source know whether the string was successfully
	             * transferred and used */
	            txtInfo.setText("BOUTON_5");
	            event.setDropCompleted(true);
	            selectedB = b5;
	            selectedB.setFill(Color.GAINSBORO);
	            b4.setFill(Color.BLACK);
	            event.consume();
	        }
	    });

	}

    @FXML
    private void handleAction(ActionEvent event) {

        txtInfo.setText("SELECTED TOOL : " + event.getSource().toString().split("'")[1]);
        stateMenu = false;
        menu.setVisible(false);
    }

    @FXML
    private void toto(DragEvent event){
    	System.out.println(((Rectangle)event.getSource()).getId());
    	System.out.println(getAllNodes(border));
    }


    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
}
