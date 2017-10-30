package melordi;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Metronome extends Parent{
	
	public Metronome() {
        //cr�ation du fond du m�tronome
        ImageView fond_metronome = new ImageView(new Image(Metronome.class.getResourceAsStream("327182.png")));
        fond_metronome.setFitHeight(40);
        fond_metronome.setPreserveRatio(true);
        
        //cr�ation de l'aiguille du m�tronome
        ImageView aiguille = new ImageView(new Image(Metronome.class.getResourceAsStream("327183.png")));
        aiguille.setFitHeight(32);
        aiguille.setPreserveRatio(true);
        aiguille.setTranslateX(16);
        aiguille.setTranslateY(2);
        
        //on applique une transformation � l'aiguille
        Rotate rotation = new Rotate(0, 3, 29);
        aiguille.getTransforms().add(rotation);
        
        //cr�ation de l'animation de l'aiguille
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 45)),
            new KeyFrame(new Duration(500), new KeyValue(rotation.angleProperty(), -45))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        this.getChildren().add(fond_metronome);
        this.getChildren().add(aiguille);
        this.setTranslateX(400);
        this.setTranslateY(200);
	}

}
