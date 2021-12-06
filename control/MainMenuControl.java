package control;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.game.*;

import java.io.IOException;
import java.net.URL;

public class MainMenuControl {
    @FXML
    private Button btnStart;
    @FXML
    private void launchGame() throws IOException {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        System.out.println("Button clicked");
        URL path = getClass().getResource("/views/Launched.fxml");
        Parent root = FXMLLoader.load(path);
        Scene scene = new Scene(root ,800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
