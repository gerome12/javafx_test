import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

public class RectangleController {


	private int i;

	@FXML
	private Rectangle rect;

	@FXML
	public void initialize() {
		System.out.println("initialize");
	}

	@FXML
	public void onclic(MouseEvent event) {
		Color p = (Color)rect.getFill();
		rect.setFill(p.darker());
	}

	public void toto() {
		System.out.println(i);
	}

	public void setVariable(int i) {
		this.i=i;
	}
}
