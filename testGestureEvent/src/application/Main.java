package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/*  w w  w.jav a 2 s . c o  m*/
public class Main extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Group root = new Group();
    Scene scene = new Scene(root, 300, 250);

    Rectangle rect = new Rectangle();
    rect.setWidth(100);
    rect.setHeight(100);
    root.getChildren().add(rect);
    rect.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        if (!event.isInertia()) {
          rect.setTranslateX(rect.getTranslateX() + event.getDeltaX());
          rect.setTranslateY(rect.getTranslateY() + event.getDeltaY());
        }
        System.out.println("Rectangle: Scroll event" + ", inertia: "
            + event.isInertia() + ", direct: " + event.isDirect());
        event.consume();
      }
    });

    rect.setOnScrollStarted(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        System.out.println("Rectangle: Scroll started event");
        event.consume();
      }
    });

    rect.setOnScrollFinished(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        System.out.println("Rectangle: Scroll finished event");
        event.consume();
      }
    });

    rect.setOnZoom(new EventHandler<ZoomEvent>() {
      @Override
      public void handle(ZoomEvent event) {
        rect.setScaleX(rect.getScaleX() * event.getZoomFactor());
        rect.setScaleY(rect.getScaleY() * event.getZoomFactor());
        System.out.println("Rectangle: Zoom event" + ", inertia: "
            + event.isInertia() + ", direct: " + event.isDirect());

        event.consume();
      }
    });

    rect.setOnZoomStarted(new EventHandler<ZoomEvent>() {
      @Override
      public void handle(ZoomEvent event) {
        System.out.println("Rectangle: Zoom event started");
        event.consume();
      }
    });

    rect.setOnZoomFinished(new EventHandler<ZoomEvent>() {
      @Override
      public void handle(ZoomEvent event) {
        System.out.println("Rectangle: Zoom event finished");
        event.consume();
      }
    });

    rect.setOnRotate(new EventHandler<RotateEvent>() {
      @Override
      public void handle(RotateEvent event) {
        rect.setRotate(rect.getRotate() + event.getAngle());
        System.out.println("Rectangle: Rotate event" + ", inertia: "
            + event.isInertia() + ", direct: " + event.isDirect());
        event.consume();
      }
    });

    rect.setOnRotationStarted(new EventHandler<RotateEvent>() {
      @Override
      public void handle(RotateEvent event) {
        System.out.println("Rectangle: Rotate event started");
        event.consume();
      }
    });

    rect.setOnRotationFinished(new EventHandler<RotateEvent>() {
      @Override
      public void handle(RotateEvent event) {
        System.out.println("Rectangle: Rotate event finished");
        event.consume();
      }
    });

    rect.setOnSwipeRight(new EventHandler<SwipeEvent>() {
      @Override
      public void handle(SwipeEvent event) {
        System.out.println("Rectangle: Swipe right event");
        event.consume();
      }
    });

    rect.setOnSwipeLeft(new EventHandler<SwipeEvent>() {
      @Override
      public void handle(SwipeEvent event) {
        System.out.println("Rectangle: Swipe left event");
        event.consume();
      }
    });

    rect.setOnTouchPressed(new EventHandler<TouchEvent>() {
      @Override
      public void handle(TouchEvent event) {
        System.out.println("Rectangle: Touch pressed event");
        event.consume();
      }
    });

    rect.setOnTouchReleased(new EventHandler<TouchEvent>() {
      @Override
      public void handle(TouchEvent event) {
        System.out.println("Rectangle: Touch released event");
        event.consume();
      }
    });

    rect.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isSynthesized()) {
          System.out.println("Ellipse: Mouse pressed event from touch"
              + ", synthesized: " + event.isSynthesized());
        }
        event.consume();
      }
    });

    rect.setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isSynthesized()) {
          System.out.println("Ellipse: Mouse released event from touch"
              + ", synthesized: " + event.isSynthesized());
        }
        event.consume();
      }
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}