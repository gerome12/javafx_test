package application;

import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
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
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;



public class mainViewController {


	@FXML
	private Canvas canvas;
	@FXML
	private AnchorPane mainPane;


	@FXML
	public void initialize() {
		System.out.println("**INFO**  initialize mainView ");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		drawBorder(gc);
		//moveCanvas(50, 30);
		
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
        new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {          
                canvas.getGraphicsContext2D().fillOval(e.getX(),e.getY(),20,20);
            }
        });
        gc.setLineWidth(1);
        gc.strokeText("Hello Canvas", 150, 100);

        Image image = new Image("file:///C:/Users/gerom/Desktop/These%20Master/JavaFX/AddressApp/canvas/src/application/1.jpg");

        gc.drawImage(image, 150, 150, 50, 50);


	}
	
	public void setParam(Scene sc) {
//		mainPane.prefWidthProperty().bind(sc.widthProperty());
//		mainPane.prefHeightProperty().bind(sc.heightProperty());
		
//		canvas.widthProperty().bind(mainPane.widthProperty());
//		canvas.heightProperty().bind(mainPane.heightProperty());
		
		sc.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		    	
		    	canvas.setWidth(sc.getWidth());
		    	mainPane.setPrefWidth(sc.getWidth());

		        System.out.println(canvas.getWidth()+"---"+mainPane.getWidth()+"---"+sc.getWidth());
		        
		        drawBorder(canvas.getGraphicsContext2D());
		        drawShapes(canvas.getGraphicsContext2D());
		    }
		});
		
		sc.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		    	
		    	canvas.setHeight(sc.getHeight());
		    	mainPane.setPrefHeight(sc.getHeight());

		        
		        
		        drawBorder(canvas.getGraphicsContext2D());
		        drawShapes(canvas.getGraphicsContext2D());
		    }
		});
		
	
	}

	
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }

    private void drawBorder(GraphicsContext gc) {
    	gc.setStroke(Color.YELLOW);
    	gc.setFill(Color.YELLOW);
        gc.fillOval(0,0, 20, 20);
        gc.fillOval(canvas.getWidth()-20, 0,20,20);
        
    	gc.setStroke(Color.YELLOWGREEN);
    	gc.setFill(Color.YELLOWGREEN);
        gc.fillOval(canvas.getWidth()-20, canvas.getHeight()-20, 20, 20);
        gc.fillOval(0, canvas.getHeight()-20,20, 20);
        
   
    }
    
    private void moveCanvas(int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }
}
