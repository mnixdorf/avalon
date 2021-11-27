package model.map;
import java.util.*;

public class Grid{
    private int rowsCount;
    private int colcount;
    ArrayList<ArrayList<Tile>> content = new ArrayList<ArrayList<Tile>>();

    private Grid(int cols, int rows){
        List<Tile> tileList = new ArrayList<Tile>();
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

            }
        }
    }

    private Grid(Grid grid){
    }
}