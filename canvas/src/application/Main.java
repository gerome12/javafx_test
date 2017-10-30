package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	mainViewController vc;


	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("ClassTest");
//        BorderPane root = new BorderPane();
		AnchorPane root= new AnchorPane();
        Scene scene = new Scene(root, 900, 500, Color.WHITE);

        // Load root layout from fxml file.
        FXMLLoader loaderVC = new FXMLLoader();
        loaderVC.setLocation(Main.class.getResource("mainView.fxml"));
        AnchorPane r = (AnchorPane) loaderVC.load();
        vc = (mainViewController) loaderVC.getController();
        
        vc.setParam(scene);

        root.getChildren().add(r);
        
        primaryStage.setScene(scene);
        primaryStage.show();


	}
	public static void main(String[] args) {
		launch(args);
	}
}
