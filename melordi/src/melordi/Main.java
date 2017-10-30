package melordi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Melordi");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        Instru monInstru = new Instru();
        Clavier monClavier = new Clavier(monInstru);
        ChangeInstru monChangeInstru = new ChangeInstru(monInstru);
        Son monSon = new Son(monClavier);
        Metronome monMetronome= new Metronome();

        monInstru.volume.bind(monSon.slider.valueProperty());

        root.getChildren().add(monClavier);
        root.getChildren().add(monMetronome);
        root.getChildren().add(monChangeInstru);
        root.getChildren().add(monSon);

        primaryStage.setScene(scene);
        primaryStage.show();
        monClavier.requestFocus();

    }
}
