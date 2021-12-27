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
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.game.*;

public class MainMenuControl{
    @FXML
    private Button btnStart;
    private PerspectiveCamera camera;
    private long timeLastMoved = System.currentTimeMillis();
    private long currentTime;

    @FXML
    private void launchGame() throws Exception {
        //Stage stage = (Stage) btnStart.getScene().getWindow();
        //System.out.println("Button clicked");
        //URL path = getClass().getResource("/views/Launched.fxml");
        //Parent root = FXMLLoader.load(path);
        //Scene scene = new Scene(root ,800, 600);
        //stage.setScene(scene);
        //stage.show();

        Stage primaryStage = (Stage) btnStart.getScene().getWindow();
        primaryStage.setResizable(false);
        Scene scene = new Scene(initRectCamera());

        //Key press events on scene
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });

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
                System.out.println("Last: " + timeLastMoved + " ||| Cur: " + currentTime);
                if(currentTime - timeLastMoved > ANIMATION_DURATION){
                    switch (event.getCode()) {
                        case ESCAPE: Platform.exit();
                            break;
                        case W:
                            TranslateTransition upTransition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            //System.out.println("KEY STROKE RECOGNIZED: w");
                            //camera.setTranslateZ(camera.getTranslateZ() + 1);
                            upTransition.setByZ(MOVE);
                            upTransition.play();

                            break;
                        case A:
                            TranslateTransition leftTansition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            //System.out.println("KEY STROKE RECOGNIZED: a");
                            //camera.setTranslateX(camera.getTranslateX() - 1);
                            leftTansition.setByX(-MOVE);
                            leftTansition.play();
                            break;
                        case S:
                            TranslateTransition downTransition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            //System.out.println("KEY STROKE RECOGNIZED: s");
                            //camera.setTranslateZ(camera.getTranslateZ() - 1);
                            downTransition.setByZ(-MOVE);
                            downTransition.play();
                            break;
                        case D:
                            TranslateTransition rightTransition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            //System.out.println("KEY STROKE RECOGNIZED: d");
                            //camera.setTranslateX(camera.getTranslateX() + 1);
                            rightTransition.setByX(MOVE);
                            rightTransition.play();
                            break;
                        case Q:
                            RotateTransition rotateLeftTransition = new RotateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            rotateLeftTransition.setAxis(camera.getRotationAxis());
                            rotateLeftTransition.setByAngle(-ROTATION_ANGLE);
                            rotateLeftTransition.play();
                        case E:
                            RotateTransition rotateRightTransition = new RotateTransition(Duration.millis(ANIMATION_DURATION), camera);
                            rotateRightTransition.setAxis(camera.getRotationAxis());
                            rotateRightTransition.setByAngle(ROTATION_ANGLE);
                            rotateRightTransition.play();
                        default:
                    }
                    timeLastMoved = System.currentTimeMillis();
                    System.out.println("Camera Layout:" + camera.getBoundsInParent());
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Parent initRectCamera() {
        Box area = new Box(5, 0, 5);
        area.setDrawMode(DrawMode.FILL);
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -15));
        camera.setRotationAxis(Rotate.Y_AXIS);
        Group root = new Group();
        root.getChildren().addAll(camera, area);
        SubScene subScene = new SubScene(root, 1280,1024);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);
        return group;
    }
}
