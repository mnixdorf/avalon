import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.game.*;

public class Main extends Application {
    private static final String TITLE = "Avalon";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root,800, 600);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        Game game = new Game();
        primaryStage.show();
        game.launch(primaryStage);
    }
}