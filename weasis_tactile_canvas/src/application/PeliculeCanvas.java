package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class PeliculeCanvas extends Canvas{

	private Image image;
	public String imageName;
	BooleanProperty lockedProperty = new SimpleBooleanProperty(false);
	
	
	public PeliculeCanvas(String image,BooleanProperty lockedProperty) {
		this.imageName = image;
		this.image = new Image(image);
		this.lockedProperty.bind(lockedProperty);
		this.setOnDragDetected(this::handleOnDragDetected);
		this.setOnDragDone(this::handleOnDragDone);
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void Draw() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		this.getGraphicsContext2D().drawImage(image, 0, 0, this.getWidth(),this.getHeight());
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
	
}
