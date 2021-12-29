package model.map;
import model.objects.Content;

import java.sql.Array;
import java.util.*;

//x * y grid of the game area, each tile of the grid represents a possibly walkable area with objects or interactions
public class Grid{
    private int rowsCount;
    private int colcount;
    private ArrayList<ArrayList<Tile>> content = new ArrayList<ArrayList<Tile>>();

    public int getColcount() {
        return colcount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public ArrayList<ArrayList<Tile>> getContent(){
        return content;
    }

    //TODO add contents
    //initialization for new games
    public Grid(int cols, int rows){
        for (int i = 0; i < rows; i++){
            ArrayList<Tile> tileList = new ArrayList<Tile>();
            for(int j = 0; j < cols; j++){
                ArrayList<Content> contents = new ArrayList<>(0);
                tileList.add(new Tile(i, j, contents));
            }
            this.content.add(tileList);
        }
    }

    //creator function for loading purposes
    public Grid(Grid grid){
        this.rowsCount = grid.getRowsCount();
        this.colcount = grid.getColcount();
        this.content = grid.getContent();
    }
}