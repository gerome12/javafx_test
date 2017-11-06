package application;

import java.util.concurrent.FutureTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MainCanvas extends Canvas{
	
	private Image image;
	public String imageName;
	
	private double zoomFactor=1;
	private double translateX=0;
	private double translateY=0;
	private double scroll=0;
	private double contrasteX=1;
	private double contrasteY=1;
	
	private ScrollController sc;
	
	public MainCanvas() {
		image =null;
		this.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	draw();
		    }
		});
		this.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	draw();
		    }
		});
	}
		
	public MainCanvas(String image) {
		this.imageName = image;
		this.image = new Image(image);
		this.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	draw();
		    }
		});
		this.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	draw();
		    }
		});
	}
	
	public void setScrollBar(ScrollController sc) {
		this.sc = sc;
	}
	
	public void setImage(String image) {
		this.imageName = image;
		this.image = new Image(image);
	}
	
	public void draw() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		this.getGraphicsContext2D().setGlobalAlpha(contrasteX);
		
		this.getGraphicsContext2D().drawImage(image, translateX, translateY, this.getWidth()*zoomFactor,this.getHeight()*zoomFactor);
		this.getGraphicsContext2D().setStroke(Color.RED);
		this.getGraphicsContext2D().setLineWidth(scroll);
		if(scroll!=0)
			this.getGraphicsContext2D().strokeRect(translateX+scroll/2, translateY+scroll/2, this.getWidth()*zoomFactor-scroll,this.getHeight()*zoomFactor-scroll);
	}
	
	public void zoom(double zoomFactor,double x,double y) {
		if(this.zoomFactor >= 1 || zoomFactor >= 1) {
			this.zoomFactor *=zoomFactor;
			this.translateX += ((translateX - x)*zoomFactor) - (translateX - x);
			this.translateY += ((translateY - y)*zoomFactor) - (translateY - y); 
		}
		draw();
	}
	public void translate(double x,double y) {
		this.translateX +=x;
		this.translateY +=y;
		draw();
	}
	public void scroll(double num) {
		scroll = num;
		draw();
	}
	public void contraste(double x, double y) {

		if(Math.abs(x) >= 6 && Math.abs(x) > Math.abs(y) && contrasteX > 0) {
			contrasteX -=0.05;
		}
		else if(Math.abs(y) >= 6 && Math.abs(x) < Math.abs(y) && contrasteX < 1) {
			contrasteX +=0.05;
		}
		draw();
	}
	
	public void reset() {
		zoomFactor=1;
		translateX=0;
		translateY=0;
		scroll=0;
		sc.setValue(0);
		contrasteX=1;
		contrasteY=1;
		draw();
	}
}
