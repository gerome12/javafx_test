package view;



import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.shape.Rectangle;


public class vuePrincipaleController {

	@FXML
	private Rectangle rect;


	@FXML
	public void initialize(){

		/*****************************************************************
		 *                         TOUCH                                 *
		 *****************************************************************/

        rect.setOnTouchPressed(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	System.out.println(event.getEventType().getName());
                System.out.println(event.getTouchCount());
                event.consume();
            }
        });
        rect.setOnTouchMoved(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	System.out.println(event.getEventType().getName());
                System.out.println(event.getTouchCount());

                event.consume();
            }
        });
        rect.setOnTouchReleased(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	System.out.println(event.getEventType().getName());
                System.out.println(event.getTouchCount());
                event.consume();
            }
        });
        rect.setOnTouchStationary(new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
            	System.out.println(event.getEventType().getName());
                System.out.println(event.getTouchCount());
                event.consume();
            }
        });

		/*****************************************************************
		 *                         SCROLL                                *
		 *****************************************************************/

        rect.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {

            	if(event.getTouchCount()==2) {

            		rect.setTranslateX(rect.getTranslateX() + event.getDeltaX());
                	rect.setTranslateY(rect.getTranslateY() + event.getDeltaY());
            	}
                System.out.println(event.getEventType().getName()+
                        ", inertia: " + event.isInertia() +
                        ", direct: " + event.isDirect());

                event.consume();
            }
        });
        rect.setOnScrollStarted(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                System.out.println(event.getEventType().getName());
                event.consume();
            }
        });
        rect.setOnScrollFinished(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                System.out.println(event.getEventType().getName());
                event.consume();
            }
        });

        /*****************************************************************
		 *                         ZOOM                                  *
		 *****************************************************************/

        rect.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override
            public void handle(ZoomEvent event) {

                rect.setScaleX(rect.getScaleX() * event.getZoomFactor());
                rect.setScaleY(rect.getScaleY() * event.getZoomFactor());
                System.out.println(event.getEventType().getName()+
                        ", inertia: " + event.isInertia() +
                        ", direct: " + event.isDirect());

                event.consume();
            }
        });
        rect.setOnZoomStarted(new EventHandler<ZoomEvent>() {
            @Override
            public void handle(ZoomEvent event) {
                System.out.println(event.getEventType().getName());
                event.consume();
            }
        });
        rect.setOnZoomFinished(new EventHandler<ZoomEvent>() {
            @Override
            public void handle(ZoomEvent event) {
                System.out.println(event.getEventType().getName());
                event.consume();
            }
        });

        /*****************************************************************
    	 *                         SWIPE                                  *
    	 *****************************************************************/

        rect.setOnSwipeRight(new EventHandler<SwipeEvent>() {
            @Override
            public void handle(SwipeEvent event) {

                System.out.println(event.getEventType().getName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                event.consume();
            }
        });

	}
}
