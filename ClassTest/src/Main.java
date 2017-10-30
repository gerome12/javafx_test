
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	private RectangleController rc;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("ClassTest");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        // Load root layout from fxml file.
        FXMLLoader loaderRC = new FXMLLoader();
        loaderRC.setLocation(Main.class.getResource("Rectangle.fxml"));
        AnchorPane r = (AnchorPane) loaderRC.load();
        rc = (RectangleController) loaderRC.getController();

        root.getChildren().add(r);
        r.setTranslateX(30);
        r.setTranslateY(30);

        rc.setVariable(23);
        rc.toto();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setFullScreenExitHint("coucou");
        primaryStage.setX(-8);
        
	}

	public static void main(String[] args) {
		launch(args);
	}
}
