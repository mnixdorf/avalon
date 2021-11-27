import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;

public class Main extends Application {
    private static final String TITLE = "Avalon";
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL path = getClass().getResource("/views/mainMenu.fxml");
        AnchorPane root = FXMLLoader.load(path);
        Scene scene = new Scene(root,800, 600);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}