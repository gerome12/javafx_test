package melordi;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Clavier extends Parent {

	private Touche[] touches;
	private Instru instru;

public Clavier(Instru ins){

		instru = ins;
	
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(400);
        fond_clavier.setHeight(200);
        fond_clavier.setArcWidth(30);
        fond_clavier.setArcHeight(30);
        fond_clavier.setFill( //on remplie notre rectangle avec un dégradé
                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                    new Stop[] {
                        new Stop(0, Color.web("#333333")),
                        new Stop(1, Color.web("#000000"))
                    }
                )
            );

        Reflection r = new Reflection();//on applique un effet de réflection
        r.setFraction(0.2);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.3);
        fond_clavier.setEffect(r);


        touches = new Touche[]{
                new Touche("Q",50,20,60,instru),
                new Touche("W",128,20,62,instru),
                new Touche("E",206,20,64,instru),
                new Touche("R",284,20,65,instru),
                new Touche("A",75,98,67,instru),
                new Touche("S",153,98,69,instru),
                new Touche("D",231,98,71,instru),
                new Touche("F",309,98,72,instru)
            };



        this.setTranslateX(50);//on positionne le groupe plutôt que le rectangle
        this.setTranslateY(250);

        this.getChildren().add(fond_clavier);//on ajoute le rectangle au groupe
        for (Touche touche: touches){	//on insère chaque touche une par une.
            this.getChildren().add(touche);
        }
        
        this.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                for (Touche touche: touches){
                    if( touche.lettre.equals( ke.getText().toUpperCase() ) )
                        touche.appuyer();
                }
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                for (Touche touche: touches){
                    if(touche.lettre.equals( ke.getText().toUpperCase() ) )
                        touche.relacher();
                }
            }
        });

    }

}
