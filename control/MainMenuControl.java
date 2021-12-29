package control;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.game.*;
import model.map.Grid;

import java.util.ArrayList;

public class MainMenuControl{
    @FXML
    private Button btnStart;
    private PerspectiveCamera camera;
    private Node[] areas;
    private Box area;
    private Box playerPlaceholder;
    private long timeLastMoved = System.currentTimeMillis();
    private long currentTime;

    //creates the rectangles representing the grid, the player and the camera
    //TODO grid
    public Parent initRectCamera(Grid map) {
        System.out.println("Cols: " + map.getColcount());
        areas = new Box[map.getColcount() * map.getRowsCount()];
        int count = 0;
        for(int i = 0; i < map.getColcount(); i++){
            for(int j = 0; j < map.getRowsCount(); j++){
                Box b = new Box(1, 0 , 1);
                b.getTransforms().add(new Translate(i,0.25,j));
                b.setDrawMode(DrawMode.LINE);
                areas[count] = b;
                System.out.println(areas.length);
                count++;
            }
        }
        System.out.println(areas.length);
        area = new Box(1, 0, 1);
        playerPlaceholder = new Box(0.5, 0.5, 0.5); //width x, height y, depth z
        area.setDrawMode(DrawMode.FILL);
        area.getTransforms().add(new Translate(0,0.25,0));
        playerPlaceholder.setMaterial(new PhongMaterial(Color.RED));
        playerPlaceholder.setDrawMode(DrawMode.FILL);
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -15));
        camera.setRotationAxis(Rotate.Y_AXIS);
        //playerPlaceholder.getTransforms().add(new Translate(0, 0, 0));
        Group root = new Group();
        root.getChildren().addAll(camera, area, playerPlaceholder);
        root.getChildren().addAll(areas);
        SubScene subScene = new SubScene(root, 1280,1024);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);
        return group;
    }

    @FXML
    private void launchGame() throws Exception {
        Stage primaryStage = (Stage) btnStart.getScene().getWindow();
        Game game = new Game();
        primaryStage.setResizable(false);
        Scene scene = new Scene(initRectCamera(game.getMap()));

        //Key press events on scene
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });

        //TODO smoother animation
        //TODO set rotation with mouse
        /*Transitions for camera/character movement
        * Uses Transitions (while key is being held) for camera-movement limited by a 500ms window for each interaction*/
        final float MOVE = 1f;
        final float ANIMATION_DURATION = 500f;
        final float ROTATION_ANGLE = 45f;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentTime = System.currentTimeMillis();
                if(currentTime - timeLastMoved > ANIMATION_DURATION){
                    switch (event.getCode()) {
                        case ESCAPE: Platform.exit();
                            break;
                        case W:
                            TranslateTransition upTransitionCamera = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            TranslateTransition upTransitionPlayer = new TranslateTransition(Duration.millis(ANIMATION_DURATION), playerPlaceholder);
                            upTransitionCamera.setByZ(MOVE);
                            upTransitionPlayer.setByZ(MOVE);
                            upTransitionCamera.play();
                            upTransitionPlayer.play();
                            break;
                        case A:
                            TranslateTransition leftTransitionCamera = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            TranslateTransition leftTansitionPlayer = new TranslateTransition(Duration.millis(ANIMATION_DURATION), playerPlaceholder);
                            leftTransitionCamera.setByX(-MOVE);
                            leftTansitionPlayer.setByX(-MOVE);
                            leftTansitionPlayer.play();
                            leftTransitionCamera.play();
                            break;
                        case S:
                            TranslateTransition downTransitionCamera = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            TranslateTransition downTransitionPlayer = new TranslateTransition(Duration.millis(ANIMATION_DURATION), playerPlaceholder);
                            downTransitionCamera.setByZ(-MOVE);
                            downTransitionPlayer.setByZ(-MOVE);
                            downTransitionCamera.play();
                            downTransitionPlayer.play();
                            break;
                        case D:
                            TranslateTransition rightTransitionCamera = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            TranslateTransition rightTransitionPlayer = new TranslateTransition(Duration.millis(ANIMATION_DURATION), playerPlaceholder);
                            rightTransitionPlayer.setByX(MOVE);
                            rightTransitionCamera.setByX(MOVE);
                            rightTransitionCamera.play();
                            rightTransitionPlayer.play();
                            break;
                        case Q:
                            RotateTransition rotateLeftTransition = new RotateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            rotateLeftTransition.setAxis(camera.getRotationAxis());
                            rotateLeftTransition.setByAngle(-ROTATION_ANGLE);
                            rotateLeftTransition.play();
                            break;
                        case E:
                            RotateTransition rotateRightTransition = new RotateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            rotateRightTransition.setAxis(camera.getRotationAxis());
                            rotateRightTransition.setByAngle(ROTATION_ANGLE);
                            rotateRightTransition.play();
                            break;
                        case F:
                            //TODO interact
                            break;
                        default:
                            break;
                    }
                    timeLastMoved = System.currentTimeMillis();
                }
            }
        });

        //Event handler for zooming in and out using the mousewheel
        //TODO correct zooming when camera is rotated
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                currentTime = System.currentTimeMillis();
                if(currentTime - timeLastMoved > ANIMATION_DURATION) {
                    double deltaY = scrollEvent.getDeltaY();
                    System.out.println(deltaY);
                    if (deltaY < 0) {
                        TranslateTransition zoomOut = new TranslateTransition(Duration.millis(500), camera);
                        zoomOut.setByZ(-MOVE);
                        zoomOut.play();
                    } else {
                        TranslateTransition zoomIn = new TranslateTransition(Duration.millis(500), camera);
                        zoomIn.setByZ(MOVE);
                        zoomIn.play();
                    }
                    timeLastMoved = System.currentTimeMillis();
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
