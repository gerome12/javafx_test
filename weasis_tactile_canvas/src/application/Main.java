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

	MainViewController mainViewConroller;
	MenuController menuController;
	ScrollController scrollController;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Weasis Tactile canvas");
		AnchorPane root= new AnchorPane();
        Scene scene = new Scene(root, Color.WHITE);


        FXMLLoader loaderVC = new FXMLLoader();
        loaderVC.setLocation(Main.class.getResource("MainView.fxml"));
        AnchorPane r = (AnchorPane) loaderVC.load();
        mainViewConroller = (MainViewController) loaderVC.getController();
        mainViewConroller.setParam(scene);


        FXMLLoader loaderMenu = new FXMLLoader();
        loaderMenu.setLocation(Main.class.getResource("MenuView.fxml"));
        Group menu = (Group) loaderMenu.load();
        menuController = (MenuController) loaderMenu.getController();
        menuController.setParam(scene, mainViewConroller.canvas, primaryStage);
        
        mainViewConroller.lockedProperty.bind(menuController.lockedProperty);
        mainViewConroller.canvas.canvasController.lockedProperty.bind(menuController.lockedProperty);


        FXMLLoader loaderScroll = new FXMLLoader();
        loaderScroll.setLocation(Main.class.getResource("ScrollView.fxml"));
        Group scroll = (Group) loaderScroll.load();
        scrollController = (ScrollController) loaderScroll.getController();
        scrollController.setParam(scene, mainViewConroller.canvas.canvasController);
        scrollController.lockedProperty.bind(menuController.lockedProperty);
        mainViewConroller.setScrollBar(scrollController);


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
