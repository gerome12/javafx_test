
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;
    @Override
	//Cette méthode est automatiquement appelée lors du lancement de l'application
	//(launch) avec la méthode main
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
	    this.primaryStage.setTitle("test menu");


	    initRootLayout();
	}

	/**
	* Initializes the root layout.
	*/
	public void initRootLayout() {
	    try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/vuePrincipale.fxml"));
	        rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		launch(args);
	}
}
