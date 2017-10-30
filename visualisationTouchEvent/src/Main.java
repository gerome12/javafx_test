
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return new Controller();
            }
        });
        fxmlLoader.setLocation(getClass().getResource("touchyfxy.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        primaryStage.setTitle("A Touching Demonstration");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
}


public static void main(String[] args) {
        launch(args);
}
        }
