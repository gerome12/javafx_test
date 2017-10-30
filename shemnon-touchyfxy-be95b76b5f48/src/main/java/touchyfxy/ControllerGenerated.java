/*
 * Copyright (c) 2013, Danno Ferrin
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *       * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *       * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *       * Neither the name of Danno Ferrin nor the
 *         names of contributors may be used to endorse or promote products
 *         derived from this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *   DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *   (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *   ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package touchyfxy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;


public class ControllerGenerated {

    @FXML
    ResourceBundle resources;

    @FXML
    URL location;

    @FXML
    TextField mouseButtonTF;

    @FXML
    TextField mouseCountTF;

    @FXML
    CheckBox mouseStillCB;

    @FXML
    CheckBox mouseSyhthesizedCB;

    @FXML
    TextField mouseXTF;

    @FXML
    TextField mouseYTF;

    @FXML
    Arc rotateArc;

    @FXML
    CheckBox rotateDirectCB;

    @FXML
    CheckBox rotateInertiaCB;

    @FXML
    TextField rotateLastTF;

    @FXML
    TextField rotateTotalTF;

    @FXML
    TextField rotateXTF;

    @FXML
    TextField rotateYTF;

    @FXML
    TextField scrollDeltaXTF;

    @FXML
    TextField scrollDeltaYTF;

    @FXML
    CheckBox scrollDirectCB;

    @FXML
    StackPane scrollEnd;

    @FXML
    Circle scrollEndCircle;

    @FXML
    Label scrollEndLabel;

    @FXML
    StackPane scrollInertia;

    @FXML
    CheckBox scrollInertiaCB;

    @FXML
    Circle scrollInertiaCircle;

    @FXML
    Label scrollInertiaLabel;

    @FXML
    StackPane scrollStart;

    @FXML
    Circle scrollStartCircle;

    @FXML
    Label scrollStartLabel;

    @FXML
    TextField scrollTotalDeltaXTF;

    @FXML
    TextField scrollTotalDeltaYTF;

    @FXML
    TextField scrollXTF;

    @FXML
    TextField scrollYTF;

    @FXML
    Group swipeArrow;

    @FXML
    TextField swipeCountTF;

    @FXML
    CheckBox swipeDirectCB;

    @FXML
    TextField swipeDirectionTF;

    @FXML
    CheckBox swipeInertiaCB;

    @FXML
    TextField swipeXTF;

    @FXML
    TextField swipeYTF;

    @FXML
    GridPane touchGridPane;

    @FXML
    CheckBox vizRotateCB;

    @FXML
    CheckBox vizScrollCB;

    @FXML
    CheckBox vizSwipeCB;

    @FXML
    CheckBox vizZoomCB;

    @FXML
    CheckBox zoomDirectCB;

    @FXML
    CheckBox zoomInertiaCB;

    @FXML
    TextField zoomLastTF;

    @FXML
    Slider zoomSlider;

    @FXML
    TextField zoomTotalTF;

    @FXML
    TextField zoomXTF;

    @FXML
    TextField zoomYTF;


    @FXML
    void contextMenuRequested(ContextMenuEvent event) {
    }

    @FXML
    void dragDrop(DragEvent event) {
    }

    @FXML
    void dragEnter(DragEvent event) {
    }

    @FXML
    void dragExit(DragEvent event) {
    }

    @FXML
    void dragOver(DragEvent event) {
    }

    @FXML
    void dragStart(MouseEvent event) {
    }

    @FXML
    void dragStop(DragEvent event) {
    }

    @FXML
    void inputMethodTextChange(InputMethodEvent event) {
    }

    @FXML
    void keyPress(KeyEvent event) {
    }

    @FXML
    void keyRelease(KeyEvent event) {
    }

    @FXML
    void keyType(KeyEvent event) {
    }

    @FXML
    void mouseClicked(MouseEvent event) {
    }

    @FXML
    void mouseDragEnter(MouseDragEvent event) {
    }

    @FXML
    void mouseDragExit(MouseDragEvent event) {
    }

    @FXML
    void mouseDragOver(MouseDragEvent event) {
    }

    @FXML
    void mouseDragRelease(MouseDragEvent event) {
    }

    @FXML
    void mouseDragged(MouseEvent event) {
    }

    @FXML
    void mouseEnter(MouseEvent event) {
    }

    @FXML
    void mouseExit(MouseEvent event) {
    }

    @FXML
    void mouseMove(MouseEvent event) {
    }

    @FXML
    void mousePress(MouseEvent event) {
    }

    @FXML
    void mouseRelease(MouseEvent event) {
    }

    @FXML
    void rotate(RotateEvent event) {
    }

    @FXML
    void rotateEnd(RotateEvent event) {
    }

    @FXML
    void rotateStart(RotateEvent event) {
    }

    @FXML
    void scroll(ScrollEvent event) {
    }

    @FXML
    void scrollEnd(ScrollEvent event) {
    }

    @FXML
    void scrollStart(ScrollEvent event) {
    }

    @FXML
    void swipeDown(SwipeEvent event) {
    }

    @FXML
    void swipeLeft(SwipeEvent event) {
    }

    @FXML
    void swipeRight(SwipeEvent event) {
    }

    @FXML
    void swipeUp(SwipeEvent event) {
    }

    @FXML
    void touchMove(TouchEvent event) {
    }

    @FXML
    void touchPress(TouchEvent event) {
    }

    @FXML
    void touchRelease(TouchEvent event) {
    }

    @FXML
    void touchStationary(TouchEvent event) {
    }

    @FXML
    void zoom(ZoomEvent event) {
    }

    @FXML
    void zoomFinish(ZoomEvent event) {
    }

    @FXML
    void zoomStart(ZoomEvent event) {
    }

    @FXML
    void initialize() {
        assert mouseButtonTF != null : "fx:id=\"mouseButtonTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert mouseCountTF != null : "fx:id=\"mouseCountTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert mouseStillCB != null : "fx:id=\"mouseStillCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert mouseSyhthesizedCB != null : "fx:id=\"mouseSyhthesizedCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert mouseXTF != null : "fx:id=\"mouseXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert mouseYTF != null : "fx:id=\"mouseYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateArc != null : "fx:id=\"rotateArc\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateDirectCB != null : "fx:id=\"rotateDirectCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateInertiaCB != null : "fx:id=\"rotateInertiaCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateLastTF != null : "fx:id=\"rotateLastTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateTotalTF != null : "fx:id=\"rotateTotalTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateXTF != null : "fx:id=\"rotateXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert rotateYTF != null : "fx:id=\"rotateYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollDeltaXTF != null : "fx:id=\"scrollDeltaXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollDeltaYTF != null : "fx:id=\"scrollDeltaYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollDirectCB != null : "fx:id=\"scrollDirectCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollEnd != null : "fx:id=\"scrollEnd\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollEndCircle != null : "fx:id=\"scrollEndCircle\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollEndLabel != null : "fx:id=\"scrollEndLabel\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollInertia != null : "fx:id=\"scrollInertia\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollInertiaCB != null : "fx:id=\"scrollInertiaCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollInertiaCircle != null : "fx:id=\"scrollInertiaCircle\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollInertiaLabel != null : "fx:id=\"scrollInertiaLabel\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollStart != null : "fx:id=\"scrollStart\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollStartCircle != null : "fx:id=\"scrollStartCircle\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollStartLabel != null : "fx:id=\"scrollStartLabel\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollTotalDeltaXTF != null : "fx:id=\"scrollTotalDeltaXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollTotalDeltaYTF != null : "fx:id=\"scrollTotalDeltaYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollXTF != null : "fx:id=\"scrollXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert scrollYTF != null : "fx:id=\"scrollYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeArrow != null : "fx:id=\"swipeArrow\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeCountTF != null : "fx:id=\"swipeCountTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeDirectCB != null : "fx:id=\"swipeDirectCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeDirectionTF != null : "fx:id=\"swipeDirectionTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeInertiaCB != null : "fx:id=\"swipeInertiaCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeXTF != null : "fx:id=\"swipeXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert swipeYTF != null : "fx:id=\"swipeYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert touchGridPane != null : "fx:id=\"touchGridPane\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert vizRotateCB != null : "fx:id=\"vizRotateCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert vizScrollCB != null : "fx:id=\"vizScrollCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert vizSwipeCB != null : "fx:id=\"vizSwipeCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert vizZoomCB != null : "fx:id=\"vizZoomCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomDirectCB != null : "fx:id=\"zoomDirectCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomInertiaCB != null : "fx:id=\"zoomInertiaCB\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomLastTF != null : "fx:id=\"zoomLastTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomSlider != null : "fx:id=\"zoomSlider\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomTotalTF != null : "fx:id=\"zoomTotalTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomXTF != null : "fx:id=\"zoomXTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";
        assert zoomYTF != null : "fx:id=\"zoomYTF\" was not injected: check your FXML file 'touchyfxy.fxml'.";


    }

}
