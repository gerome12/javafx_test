package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PeliculeCanvas extends Canvas{

	private Image image;
	public String imageName;
	
	public PeliculeCanvas() {
		image =null;
	}
	
	public PeliculeCanvas(String image) {
		this.imageName = image;
		this.image = new Image(image);
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void Draw() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
		this.getGraphicsContext2D().drawImage(image, 0, 0, this.getWidth(),this.getHeight());
	}
	
}
