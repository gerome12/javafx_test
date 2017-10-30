package view;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;

public class vuePrincipaleController {
	@FXML
	private ImageView im1;
	@FXML
	private ImageView imdest;
	@FXML
	private ImageView imdest1;

	@FXML
	private VBox vboxSrc;
	@FXML
	private VBox vboxDst;

	@FXML
	private Text source;
	@FXML
	private Text target;

    @FXML
    public void initialize() {

		System.out.println("init");

		for(Node child: vboxSrc.getChildren()) {

			if (child instanceof ImageView) {

				child.setOnDragDetected(new EventHandler <MouseEvent>() {
			        public void handle(MouseEvent event) {
			            /* drag was detected, start drag-and-drop gesture*/
			            System.out.println("onDragDetected");

			            /* allow any transfer mode */
			            Dragboard db = child.startDragAndDrop(TransferMode.COPY);

			            db.setDragView(child.snapshot(null, null));

			            /* put a string on dragboard */
			            ClipboardContent content = new ClipboardContent();
			            content.putImage(((ImageView)child).getImage());
			            db.setContent(content);

			            event.consume();
			        }
			    });

				child.setOnDragDone(new EventHandler <DragEvent>() {
			        public void handle(DragEvent event) {
			            /* the drag-and-drop gesture ended */
			            System.out.println("onDragDone");
			            /* if the data was successfully moved, clear it */
			            if (event.getTransferMode() == TransferMode.COPY) {
			            	//vbox.getChildren().remove((Node)child);
			                //im1.setImage(null);
			            	((ImageView) child).setImage(event.getDragboard().getImage());

			            }

			            event.consume();
			        }
			    });
			}
		}

		for(Node child: vboxDst.getChildren()) {

			if (child instanceof ImageView)
			{
				child.setOnDragOver(new EventHandler <DragEvent>() {
			        public void handle(DragEvent event) {
			            /* data is dragged over the target */
			            System.out.println("onDragOver");

			            /* accept it only if it is  not dragged from the same node
			             * and if it has a string data */
			            if (event.getGestureSource() != child && event.getDragboard().hasImage()) {
			                /* allow for both copying and moving, whatever user chooses */
			                event.acceptTransferModes(TransferMode.COPY);
			            }
			            event.consume();
			        }
			    });

				child.setOnDragEntered(new EventHandler <DragEvent>() {
			        public void handle(DragEvent event) {
			            /* the drag-and-drop gesture entered the target */
			            System.out.println("onDragEntered");
			            /* show to the user that it is an actual gesture target */
			            if (event.getGestureSource() != child && event.getDragboard().hasImage()) {
			            	//imdest.setFill(Color.GREEN);
			            }

			            event.consume();
			        }
			    });

				child.setOnDragExited(new EventHandler <DragEvent>() {
			        public void handle(DragEvent event) {
			            /* mouse moved away, remove the graphical cues */
			        	//imdest.setFill(Color.BLACK);

			            event.consume();
			        }
			    });

				child.setOnDragDropped(new EventHandler <DragEvent>() {
			        public void handle(DragEvent event) {
			            /* data dropped */
			            System.out.println("onDragDropped");
			            /* if there is a string data on dragboard, read it and use it */
			            Dragboard db = event.getDragboard();
			            boolean success = false;
			            if (db.hasImage()) {
			            	((ImageView)child).setImage(db.getImage());
			                success = true;
			            }
			            /* let the source know whether the string was successfully
			             * transferred and used */
			            event.setDropCompleted(success);

			            event.consume();
			        }
			    });
			}
		}




















	    source.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                db.setContent(content);

                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    target.setFill(Color.GREEN);
                }

                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                target.setFill(Color.BLACK);

                event.consume();
            }
        });

        target.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    target.setText(db.getString());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        source.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setText("done");
                }

                event.consume();
            }
        });

	}

}

