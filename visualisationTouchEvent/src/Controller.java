import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;

public class Controller extends ControllerGenerated {

    private static void centerInBounds(double x, double y, Node target) {
        Bounds bounds = target.getBoundsInLocal();
        target.setLayoutX(x - bounds.getMinX() - bounds.getWidth() / 2);
        target.setLayoutY(y - bounds.getMinY() - bounds.getHeight() / 2);
    }

    NumberFormat doubleFormat = new DecimalFormat("0.0");
    NumberFormat double3Format = new DecimalFormat("0.000");

    Timeline swipeFade;
    Timeline rotateFade;
    Timeline zoomFade;
    Timeline scrollFade;

    @Override
    void initialize() {
        super.initialize();

        rotateArc.visibleProperty().bind(vizRotateCB.selectedProperty());
        scrollEnd.visibleProperty().bind(vizScrollCB.selectedProperty());
        scrollInertia.visibleProperty().bind(vizScrollCB.selectedProperty());
        scrollStart.visibleProperty().bind(vizScrollCB.selectedProperty());
        swipeArrow.visibleProperty().bind(vizSwipeCB.selectedProperty());
        zoomSlider.visibleProperty().bind(vizZoomCB.selectedProperty());

        sizeScrollCircles();

        swipeFade = new Timeline(
                new KeyFrame(new Duration(1000),
                     new KeyValue(swipeArrow.opacityProperty(), 0)
                )
        );
        rotateFade = new Timeline(
                new KeyFrame(new Duration(1000),
                     new KeyValue(rotateArc.opacityProperty(), 0)
                )
        );
        zoomFade = new Timeline(
                new KeyFrame(new Duration(1000),
                     new KeyValue(zoomSlider.opacityProperty(), 0)
                )
        );
        scrollFade = new Timeline(
                new KeyFrame(new Duration(1000),
                    new KeyValue(scrollStart.opacityProperty(), 0),
                    new KeyValue(scrollEnd.opacityProperty(), 0),
                    new KeyValue(scrollInertia.opacityProperty(), 0)
                )
        );
    }

    private void sizeScrollCircles() {
        scrollEnd.layout();
        scrollInertia.layout();
        scrollStart.layout();
        double radius = Math.max(
                Math.sqrt(scrollEndLabel.prefWidth(-1) * scrollEndLabel.prefWidth(-1) + scrollEndLabel.prefHeight(-1) * scrollEndLabel.prefHeight(-1)),
                Math.max(
                        Math.sqrt(scrollInertiaLabel.prefWidth(-1) * scrollInertiaLabel.prefWidth(-1) + scrollInertiaLabel.prefHeight(-1) * scrollInertiaLabel.prefHeight(-1)),
                        Math.sqrt(scrollStartLabel.prefWidth(-1) * scrollStartLabel.prefWidth(-1) + scrollStartLabel.prefHeight(-1) * scrollStartLabel.prefHeight(-1)))
        ) / 2;

        scrollEndCircle.setRadius(radius);
        scrollInertiaCircle.setRadius(radius);
        scrollStartCircle.setRadius(radius);
        scrollEnd.layout();
        scrollInertia.layout();
        scrollStart.layout();
    }

    @Override
    void swipeDown(SwipeEvent event) {
        swipeArrow.setRotate(180);
        updateSwipe(event);
    }

    @Override
    void swipeLeft(SwipeEvent event) {
        swipeArrow.setRotate(270);
        updateSwipe(event);
    }

    @Override
    void swipeRight(SwipeEvent event) {
        swipeArrow.setRotate(90);
        updateSwipe(event);
    }

    @Override
    void swipeUp(SwipeEvent event) {
        swipeArrow.setRotate(0);
        updateSwipe(event);
    }

    private void updateSwipe(SwipeEvent event) {
        swipeXTF.setText(doubleFormat.format(event.getX()));
        swipeYTF.setText(doubleFormat.format(event.getY()));
        swipeDirectionTF.setText(event.getEventType().toString());
        swipeCountTF.setText(Integer.toString(event.getTouchCount()));
        swipeInertiaCB.setSelected(event.isInertia());
        swipeDirectCB.setSelected(event.isDirect());

        centerInBounds(event.getX(), event.getY(), swipeArrow);
        swipeArrow.setOpacity(1.0);
        swipeFade.playFromStart();
    }

    @Override
    void zoom(ZoomEvent event) {
        updateZoom(event);
    }

    @Override
    void zoomFinish(ZoomEvent event) {
        updateZoom(event);
        zoomFade.playFromStart();
    }

    @Override
    void zoomStart(ZoomEvent event) {
        updateZoom(event);
        zoomFade.jumpTo(Duration.ZERO);
        zoomFade.stop();
        zoomSlider.setOpacity(1.0);
    }

    private void updateZoom(ZoomEvent event) {
        zoomXTF.setText(doubleFormat.format(event.getX()));
        zoomYTF.setText(doubleFormat.format(event.getY()));
        zoomTotalTF.setText(double3Format.format(event.getTotalZoomFactor()));
        if (!Double.isNaN(event.getZoomFactor()) && event.getZoomFactor() != 1.0) {
            zoomLastTF.setText(double3Format.format(event.getZoomFactor()));
        }
        zoomInertiaCB.setSelected(event.isInertia());
        zoomDirectCB.setSelected(event.isDirect());

        double zoom = event.getTotalZoomFactor();
        if (zoom > 1.0) {
            zoom = 2 - (1/zoom);
        }
        zoomSlider.setValue(zoom);

        centerInBounds(event.getX(), event.getY(), zoomSlider);
    }

    @Override
    void scroll(ScrollEvent event) {
        updateScroll(event);
        if (event.isInertia()) {
            double x = event.getX();
            double y = event.getY();
            if (!event.isDirect()) {
                x -= event.getTotalDeltaX();
                y -= event.getTotalDeltaY();
            }
            centerInBounds(x, y, scrollInertia);
            scrollInertia.setOpacity(1.0);
            scrollStart.setOpacity(1.0);
            scrollEnd.setOpacity(1.0);
            scrollFade.playFromStart();
        } else {
            double x = event.getX();
            double y = event.getY();
            if (!event.isDirect()) {
                x -= event.getTotalDeltaX();
                y -= event.getTotalDeltaY();
            }
            centerInBounds(x, y, scrollEnd);
        }
    }

    @Override
    void scrollEnd(ScrollEvent event) {
        updateScroll(event);
        double x = event.getX();
        double y = event.getY();
        if (!event.isDirect()) {
            x -= event.getTotalDeltaX();
            y -= event.getTotalDeltaY();
        }
        centerInBounds(x, y, scrollEnd);
        scrollFade.playFromStart();
    }

    @Override
    void scrollStart(ScrollEvent event) {
        updateScroll(event);

        double x = event.getX();
        double y = event.getY();

        centerInBounds(x, y, scrollStart);

        if (!event.isDirect()) {
            x -= event.getTotalDeltaX();
            y -= event.getTotalDeltaY();
        }
        centerInBounds(x, y, scrollEnd);
        scrollFade.stop();
        scrollFade.jumpTo(Duration.ZERO);

        scrollStart.setOpacity(1.0);
        scrollEnd.setOpacity(1.0);
        scrollInertia.setOpacity(0.0);
    }

    private void updateScroll(ScrollEvent event) {
        sizeScrollCircles();
        scrollXTF.setText(doubleFormat.format(event.getX()));
        scrollYTF.setText(doubleFormat.format(event.getY()));
        scrollDeltaXTF.setText(doubleFormat.format(event.getDeltaX()));
        scrollDeltaYTF.setText(doubleFormat.format(event.getDeltaY()));
        scrollTotalDeltaXTF.setText(doubleFormat.format(event.getTotalDeltaX()));
        scrollTotalDeltaYTF.setText(doubleFormat.format(event.getTotalDeltaY()));
        scrollInertiaCB.setSelected(event.isInertia());
        scrollDirectCB.setSelected(event.isDirect());
    }


    @Override
    void rotate(RotateEvent event) {
        updateRotate(event);
    }

    @Override
    void rotateEnd(RotateEvent event) {
        updateRotate(event);
        rotateFade.playFromStart();
    }

    @Override
    void rotateStart(RotateEvent event) {
        updateRotate(event);
        rotateFade.jumpTo(Duration.ZERO);
        rotateFade.stop();
        rotateArc.setOpacity(1.0);
    }

    private void updateRotate(RotateEvent event) {
        rotateXTF.setText(doubleFormat.format(event.getX()));
        rotateYTF.setText(doubleFormat.format(event.getY()));
        rotateTotalTF.setText(double3Format.format(event.getTotalAngle()));
        if (!Double.isNaN(event.getAngle()) && event.getAngle()!= 1.0) {
            rotateLastTF.setText(double3Format.format(event.getAngle()));
        }
        rotateInertiaCB.setSelected(event.isInertia());
        rotateDirectCB.setSelected(event.isDirect());

        rotateArc.setLength(-event.getTotalAngle());

        rotateArc.setLayoutX(event.getX());
        rotateArc.setLayoutY(event.getY());
    }

    @Override
    void touchMove(TouchEvent event) {
        updateTouch(event);
    }

    @Override
    void touchPress(TouchEvent event) {
        updateTouch(event);
    }

    @Override
    void touchRelease(TouchEvent event) {
        updateTouch(event);
    }

    @Override
    void touchStationary(TouchEvent event) {
        updateTouch(event);
    }

    private void updateTouch(TouchEvent event) {
    	System.out.println(event.getEventType().getName());
        // fitst clear old values
        //TODO use streams api for this
        Collection<Node> keepers = new ArrayList<>();
        for (Node n : touchGridPane.getChildren()) {
            if (GridPane.getRowIndex(n) == 0) {
                keepers.add(n);
            }
        }
        touchGridPane.getChildren().retainAll(keepers);

        //now add the new values

        int row = 1;
        for (TouchPoint tp : event.getTouchPoints()) {
            Label num = new Label(Integer.toString(tp.getId()));
            GridPane.setRowIndex(num, row);
            GridPane.setColumnIndex(num, 0);

            Label x = new Label(doubleFormat.format(tp.getX()));
            GridPane.setRowIndex(x, row);
            GridPane.setColumnIndex(x, 1);

            Label y = new Label(doubleFormat.format(tp.getY()));
            GridPane.setRowIndex(y, row);
            GridPane.setColumnIndex(y, 2);

            Label t = new Label(tp.getState().toString().substring(0, 1));
            GridPane.setRowIndex(t, row);
            GridPane.setColumnIndex(t, 3);

            touchGridPane.getChildren().addAll(num, x, y, t);
            row++;
        }
    }

    @Override
    void mouseDragged(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mouseClicked(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mouseEnter(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mouseMove(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mouseExit(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mouseRelease(MouseEvent event) {
        updateMouse(event);
    }

    @Override
    void mousePress(MouseEvent event) {
        updateMouse(event);
    }

    private void updateMouse(MouseEvent event) {
        mouseXTF.setText(doubleFormat.format(event.getX()));
        mouseYTF.setText(doubleFormat.format(event.getY()));
        mouseButtonTF.setText(event.getButton().toString());
        mouseCountTF.setText(Integer.toString(event.getClickCount()));
        mouseSyhthesizedCB.setSelected(event.isSynthesized());
        mouseStillCB.setSelected(event.isStillSincePress());
    }
}
