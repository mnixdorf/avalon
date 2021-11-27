package model.game;
import javafx.stage.Stage;
import model.player.*;
import model.map.*;

public class Game {
    private Grid map;
    private Player player;

    public Game(){
        System.out.println("Init");

       //TODO new model.game launch
    }

    public Game(String save){
        //TODO savegame
    }

    private void initialize(){

    }

    public void launch(Stage primaryStage){
        System.out.println("Game started");
    }
}
