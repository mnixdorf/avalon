package control;
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
import model.game.*;

public class MainMenuControl{
    @FXML
    private Button btnStart;

    PerspectiveCamera camera;
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
        //TODO use animation for camera movement

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE: Platform.exit();
                        break;
                    case W:
                        System.out.println("KEY STROKE RECOGNIZED: w");
                        camera.setTranslateZ(camera.getTranslateZ() + 1);
                        break;
                    case A:
                        System.out.println("KEY STROKE RECOGNIZED: a");
                        camera.setTranslateX(camera.getTranslateX() - 1);
                        break;
                    case S:
                        System.out.println("KEY STROKE RECOGNIZED: s");
                        camera.setTranslateZ(camera.getTranslateZ() - 1);
                        break;
                    case D:
                        System.out.println("KEY STROKE RECOGNIZED: d");
                        camera.setTranslateX(camera.getTranslateX() + 1);
                        break;
                    default:
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
        Group root = new Group();
        root.getChildren().addAll(camera, area);
        SubScene subScene = new SubScene(root, 1280,1024);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);
        return group;
    }
}
