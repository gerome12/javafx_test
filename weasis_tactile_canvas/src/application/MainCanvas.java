package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainCanvas extends Canvas{
	
	private Image image;
	public String imageName;
	
	private DoubleProperty zoomFactorProperty = new SimpleDoubleProperty(1);
	private double translateX=0;
	private double translateY=0;
	private double scroll=0;
	private double contrasteX=1;
	private double contrasteY=1;
	
	private ParallelTransition parallelTransition;
	
	private ScrollController sc;
	
	public MainCanvas() {

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
		
		this.zoomFactorProperty.addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	draw();
		    }
		});
		initTimeLine();
	}
		
	
	private void initTimeLine() {
		parallelTransition = new ParallelTransition();
		
		Timeline timeline;
		KeyValue kv;
		KeyFrame kf;
    	timeline = new Timeline();
    	kv = new KeyValue(zoomFactorProperty, 1);
    	kf = new KeyFrame(Duration.millis(1000), kv);
    	timeline.getKeyFrames().add(kf);
		
        parallelTransition.getChildren().addAll(timeline);
	}
	
	public void setScrollBar(ScrollController sc) {
		this.sc = sc;
	}
	
	public void setImage(String image) {
		this.imageName = image;
		this.image = new Image(image);
	}
	
	public void draw() {

		translateX = (translateX > this.getWidth() / 2) ?  this.getWidth() / 2 : translateX;
		translateY = (translateY > this.getHeight() / 2) ?  this.getHeight() / 2 : translateY;
		
		translateX = translateX < -this.getWidth() * zoomFactorProperty.get() + (this.getWidth() / 2) ?  -this.getWidth() * zoomFactorProperty.get() + (this.getWidth() / 2) : translateX;
		translateY = translateY < -this.getHeight() * zoomFactorProperty.get() + (this.getHeight() / 2) ?  -this.getHeight() * zoomFactorProperty.get() + (this.getHeight() / 2) : translateY;
		

			
			
		
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		this.getGraphicsContext2D().setGlobalAlpha(contrasteX);
		
		this.getGraphicsContext2D().drawImage(image, translateX, translateY, this.getWidth()*this.zoomFactorProperty.get(),this.getHeight()*this.zoomFactorProperty.get());
		this.getGraphicsContext2D().setStroke(Color.RED);
		this.getGraphicsContext2D().setLineWidth(scroll);
		if(scroll!=0)
			this.getGraphicsContext2D().strokeRect(translateX+scroll/2, translateY+scroll/2, this.getWidth()*this.zoomFactorProperty.get()-scroll,this.getHeight()*this.zoomFactorProperty.get()-scroll);
	}
	
	public void zoom(double zoomFactor,double x,double y) {
		if(this.zoomFactorProperty.get() >= 0.75  || zoomFactor > 1) {
			this.zoomFactorProperty.set(zoomFactor * this.zoomFactorProperty.get());
			this.translateX += ((translateX - x)*zoomFactor) - (translateX - x);
			this.translateY += ((translateY - y)*zoomFactor) - (translateY - y); 
		}
		draw();
	}
	
	public void zoomEnd() {
		if(this.zoomFactorProperty.get() < 1) {
			//parallelTransition.playFromStart();
			zoomFactorProperty.set(1);
			this.translateX = 0;
			this.translateY = 0; 
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

		if(Math.abs(x) > Math.abs(y) && contrasteX > 0) {
			contrasteX -=Math.abs(x)*0.0002;
		}
		else if(Math.abs(x) < Math.abs(y) && contrasteX < 1) {
			contrasteX +=Math.abs(y)*0.0002;
		}
		draw();
	}
	
	public void reset() {
		this.zoomFactorProperty.set(1);
		translateX=0;
		translateY=0;
		scroll=0;
		sc.setValue(0);
		contrasteX=1;
		contrasteY=1;
		draw();
	}
}
