package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class Main extends Application {

	mainViewController vc;
	MenuController mc;
	ScrollController sc;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Weasis Tactile canvas");
		AnchorPane root= new AnchorPane();
        Scene scene = new Scene(root, 900, 500, Color.WHITE);

        // Load root layout from fxml file.
        FXMLLoader loaderVC = new FXMLLoader();
        loaderVC.setLocation(Main.class.getResource("mainView.fxml"));
        AnchorPane r = (AnchorPane) loaderVC.load();
        vc = (mainViewController) loaderVC.getController();
        vc.setParam(scene);


        // Load root layout from fxml file.
        FXMLLoader loaderMenu = new FXMLLoader();
        loaderMenu.setLocation(Main.class.getResource("Menu.fxml"));
        Group menu = (Group) loaderMenu.load();
        mc = (MenuController) loaderMenu.getController();
        mc.setParam(scene,vc.canvas);
        
        vc.lockedProperty.bind(mc.lockedProperty);

        // Load root layout from fxml file.
        FXMLLoader loaderScroll = new FXMLLoader();
        loaderScroll.setLocation(Main.class.getResource("Scroll.fxml"));
        Group scroll = (Group) loaderScroll.load();
        sc = (ScrollController) loaderScroll.getController();
        sc.setParam(scene, vc);
        sc.lockedProperty.bind(mc.lockedProperty);
        vc.setScrollBar(sc);

//        root.setCenter(r);
        root.getChildren().add(r);
        root.getChildren().add(scroll);
        root.getChildren().add(menu);


        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreen(false);
        primaryStage.setHeight(500);
        primaryStage.setWidth(900);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
